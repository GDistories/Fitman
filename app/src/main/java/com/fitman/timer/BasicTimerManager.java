package com.fitman.timer;

import android.os.Handler;
import android.os.Message;

import java.util.*;


final class BasicTimerManager {
    private static BasicTimerManager mInstance;
    private Map<BasicTimer, BasicTimerInfo> mInfos = new HashMap<BasicTimer, BasicTimerInfo>();
    private Timer mTimer = new Timer();
    private static final int BASE_INTERAL_MILLISEC = 100;

    private BasicTimerManager(){

        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                doRun();
            }
        }, 0, BASE_INTERAL_MILLISEC);
    }

    public static BasicTimerManager getInstance(){
        if (mInstance == null){
            mInstance = new BasicTimerManager();
        }

        return mInstance;
    }

    public void addTimer(BasicTimer timer, BasicTimer.BasicTimerCallback cb){
        BasicTimerInfo info = new BasicTimerInfo();
        info.cb = cb;
        info.init = Integer.MAX_VALUE;
        info.count = Integer.MAX_VALUE;

        mInfos.put(timer, info);
    }

    public void start(BasicTimer timer, int millisec){
        startTimer(timer, millisec, false);
    }

    public void startOneshot(BasicTimer timer, int millisec){
        startTimer(timer, millisec, true);
    }

    public void stop(BasicTimer timer){
        if (mInfos.containsKey(timer)){
            BasicTimerInfo info = mInfos.get(timer);
            info.init = Integer.MAX_VALUE;
            info.count = Integer.MAX_VALUE;
        }
    }

    public void remove(BasicTimer timer){
        if (mInfos.containsKey(timer)){
            mInfos.remove(timer);
        }
    }

    public boolean isRunning(BasicTimer timer){
        boolean running = false;
        if (mInfos.containsKey(timer)){
            BasicTimerInfo info = mInfos.get(timer);

            running = !(info.init == Integer.MAX_VALUE && info.count == Integer.MAX_VALUE);
        }
        return running;
    }

    private void startTimer(BasicTimer timer, int millisec, boolean oneshot){
        if (mInfos.containsKey(timer)){
            BasicTimerInfo info = mInfos.get(timer);
            info.count = millisec / BASE_INTERAL_MILLISEC;
            info.init = millisec / BASE_INTERAL_MILLISEC;
            info.oneshot = oneshot;

            //???????????????0
            info.count = Math.max(info.count, 1);
            info.count = Math.max(info.init, 1);
        }
    }

    private void doRun(){
        List<Object> ls = new ArrayList<Object>();

        for (Map.Entry entry: mInfos.entrySet()){
            BasicTimerInfo info = (BasicTimerInfo)entry.getValue();
            if(--info.count == 0) {
                if (info.oneshot){
                    info.count = Integer.MAX_VALUE;
                    info.init = Integer.MAX_VALUE;
                }else {
                    info.count = info.init;
                }
                //??????????????????????????????????????????????????????????????????
                ls.add(entry);
            }
        }

        if (!ls.isEmpty()){
            Message message = new Message();
            message.what = 0;
            message.obj = ls;
            mHander.sendMessage(message);
        }
    }

    private Handler mHander = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            List<Object> ls = (List<Object>) msg.obj;
            for (Object o: ls){
                Map.Entry entry = (Map.Entry)o;
                BasicTimer timer = (BasicTimer)entry.getKey();
                BasicTimerInfo info = (BasicTimerInfo)entry.getValue();

                ((BasicTimer.BasicTimerCallback)info.cb).onTimer();
                if (info.oneshot){ //?????????????????????????????????????????????
                    remove(timer);
                }
            }
            return false;
        }
    });
}


