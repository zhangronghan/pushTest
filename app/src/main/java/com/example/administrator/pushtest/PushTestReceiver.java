package com.example.administrator.pushtest;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.baidu.android.pushservice.PushMessageReceiver;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */

public class PushTestReceiver extends PushMessageReceiver {

    @Override
    public void onBind(Context context, int i, String s, String s1, String s2, String s3) {
        String str="已绑定"+"  erroeCode="+ i +" appid="+ s +"  useId="+ s1 +"  channeId="+ s2 +"  requestId"+ s3;
        Log.d("PushTestReceiver",">>>onBind"+ str +"<<<");

        getContent(context,str);

    }


    @Override
    public void onUnbind(Context context, int i, String s) {
        Log.d("PushTestReceiver",">>>onUnbind<<<");
        String responseString = "解除绑定"+" onUnbind errorCode=" + i
                + " requestId = " + s;

        getContent(context,responseString);
    }

    @Override
    public void onSetTags(Context context, int i, List<String> list, List<String> list1, String s) {
        String responseString = "onSetTags errorCode=" + i
                + " sucessTags=" + list + " failTags=" + list1
                + " requestId=" + s;

        Log.d("PushTestReceiver",">>>onSetTags<<<");

        getContent(context,responseString);
    }

    @Override
    public void onDelTags(Context context, int i, List<String> list, List<String> list1, String s) {
        Log.d("PushTestReceiver",">>>onDelTags<<<");
        String responseString = "onDelTags errorCode=" + i
                + " sucessTags=" + list + " failTags=" + list1
                + " requestId=" + s;

        getContent(context,responseString);
    }

    @Override
    public void onListTags(Context context, int i, List<String> list, String s) {
        Log.d("PushTestReceiver",">>>onBind<<<");
        String responseString = "onListTags errorCode=" + i + " tags="
                + list;

        getContent(context,responseString);
    }

    @Override
    public void onMessage(Context context, String message, String customContentString) {
        Log.d("PushTestReceiver",">>>onMessage<<<");

        String content="透传消息"+"  message:"+message+"  customContentString:"+customContentString;
        Log.d("PushTestReceiver",">>>"+ content +"<<<");
        getContent(context,content);
    }

    //接收通知点击的函数
    @Override
    public void onNotificationClicked(Context context, String s, String s1, String s2) {
        Log.d("PushTestReceiver",">>>onNotificationClicked<<<");

        String notifyString = "通知点击 title=\"" + s + "\" description=\""
                + s1 + "\" customContent=" + s2;
        Log.e(TAG, notifyString);
        getContent(context,notifyString);
    }

    //接收通知到达的函数
    @Override
    public void onNotificationArrived(Context context, String s, String s1, String s2) {
        Log.d("PushTestReceiver",">>>onNotificationArrived<<<");

    }
    private void getContent(Context context, String str) {
        Intent intent=new Intent(context.getApplicationContext(),MainActivity.class);
        intent.putExtra("result",str);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.getApplicationContext().startActivity(intent);
    }

}
