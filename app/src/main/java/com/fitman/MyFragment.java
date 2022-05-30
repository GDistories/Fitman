package com.fitman;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fitman.database.User.UserDao;
import com.fitman.utils.SharedPreferencesUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class MyFragment extends BaseFragment {
    private static final String TAG = "MyFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        ImageView im_profile = getView().findViewById(R.id.im_profile);
        TextView tv_username = getView().findViewById(R.id.tv_username);
        ImageView im_sex = getView().findViewById(R.id.im_sex);
        TextView tv_myData = getView().findViewById(R.id.tv_myData);
        TextView tv_profile = getView().findViewById(R.id.tv_profile);
        TextView tv_setting = getActivity().findViewById(R.id.txt_setting);
        TextView tv_feedback = getActivity().findViewById(R.id.txt_feedback);
        TextView tv_update = getActivity().findViewById(R.id.txt_update);
        TextView tv_about = getActivity().findViewById(R.id.txt_about);

        UserDao userDao = new UserDao(getActivity());

        if (isLogin()){
            //已登录
            Log.e(TAG, "Login");
            getUserPhoto(getContext().getCacheDir().getAbsolutePath() + "/" + getUsername() + ".jpg");
            tv_username.setText(userDao.getFirstName(getUsername())+" "+userDao.getLastName(getUsername()));
            im_sex.setVisibility(View.VISIBLE);
            switch (userDao.getGender(getUsername())) {
                case "":
                    im_sex.setVisibility(View.GONE);
                    break;
                case "Female":
                    im_sex.setImageResource(R.drawable.ic_sex_female);
                    break;
                case "Male":
                    im_sex.setImageResource(R.drawable.ic_sex_male);
                    break;
            }
        }else {
            //未登录
            Log.e(TAG, "UnLogin");
            im_profile.setImageResource(R.drawable.default_profile_pic);
            tv_username.setText(getString(R.string.username_unlogin));
            im_sex.setVisibility(View.GONE);
        }


        im_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLogin()){
                    openAlbum();
                }else{
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
//                    getActivity().finish();
                }

            }
        });

        tv_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLogin()){
                    Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
//                    getActivity().finish();
                }
            }
        });

        tv_myData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLogin()){
                    Intent intent = new Intent(getActivity(), MyDataActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
//                    getActivity().finish();
                }
            }
        });

        tv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLogin()){
                    Intent intent = new Intent(getActivity(), UserProfileActivity.class);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
//                    getActivity().finish();
                }
            }
        });

        tv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SettingActivity.class));
            }
        });

        tv_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), FeedbackActivity.class));
            }
        });

        tv_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), R.string.searching_update, Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(), R.string.no_update, Toast.LENGTH_SHORT).show();
            }
        });

        tv_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AboutActivity.class));
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    //打开相册，选择图片
    private void openAlbum(){
        Intent intent2 = new Intent("android.intent.action.PICK");
        intent2.setType("image/*");
        startActivityForResult(intent2, 1);
    }

    public void onPhoto(Uri uri, int outputX, int outputY) {
        Intent intent = null;
        intent = new Intent("com.android.camera.action.CROP");

        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", true);
        intent.putExtra("circleCrop", true);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        startActivityForResult(intent, 2);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:              //图库选择回调
                if (data != null) {
                    Uri uri = data.getData();
                    onPhoto(uri, 300, 300);
                }
                break;
            case 2:             //图片裁剪后回调
                if (data != null) {
                    Bundle extras = data.getExtras();
                    Bitmap bitmap = (Bitmap) extras.get("data");
                    if (bitmap != null) {
                        saveBitmap(bitmap, getActivity(), getContext().getCacheDir().getAbsolutePath() + "/" + getUsername() + ".jpg");
                        ImageView im_profile = getView().findViewById(R.id.im_profile);
                        im_profile.setImageBitmap(bitmap);
                    }
                }
                break;
        }
    }

    public void saveBitmap(Bitmap bitmap, Context ct, String savePath) {
        File filePic;
        try {
            filePic = new File(savePath);
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(filePic);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            return;
        }
    }

    public Bitmap readBitmap(Context ct, String savePath) {
        Bitmap bitmap;
        try {
            File filePic = new File(savePath);
            if (!filePic.exists()) {
                return null;
            }
            bitmap = BitmapFactory.decodeFile(savePath);
            return bitmap;
        } catch (Exception e) {
            return null;
        }
    }

    public void getUserPhoto(String savePath) {
        ImageView im_profile = getView().findViewById(R.id.im_profile);
        File file = new File(savePath);
        if (!file.exists()){
            im_profile.setImageResource(R.drawable.default_profile_pic);
        }
        else
        {
            Bitmap bitmap = readBitmap(getContext(), savePath);
            im_profile.setImageBitmap(bitmap);
        }



    }


}