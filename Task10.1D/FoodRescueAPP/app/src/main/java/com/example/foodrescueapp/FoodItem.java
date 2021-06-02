package com.example.foodrescueapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.alipay.sdk.app.PayTask;
import com.example.foodrescueapp.data.DatabaseHelper;
import com.example.foodrescueapp.model.Food;
import com.example.foodrescueapp.util.OrderInfoUtil2_0;

import java.util.Map;

public class FoodItem extends AppCompatActivity {
    ImageView foodItemImageView;
    TextView foodItemTitleTextView, foodItemDescriptionTextView, foodItemDateTextView, foodItemTimeTextView, foodItemQuantityTextView, foodItemLocationTextView;
    int position;
    Food foodItem;
    DatabaseHelper db;
    private static final int SDK_PAY_FLAG = 1001;
    private String RSA_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCtPkwmYfaOKj9cO5EMRFbLL5rO0Wxp78Lorr7JxxDgnRIwGx9uoECHGAPo/6DTypOF2eJTzNLLZKCq6RJtO1zBR8JwJIsfrJWPMXI5s5tVchtjH5eLn+UiPwpxh0YdQOLtSjxQ4OdUokvq/kO4C5dvvpdcR7znyr4I5zVr9L7ZzFQMlxWyC9GpTnbo8pIzP2MuKLMl7dVwM6s2HbXEFVkofirlaJpmdM4By4JqPfO3G1wtbGAgJooJkwTBWFKcCIq4HUQWF4fQmo0rnuDvRY6JbyveRevjqNpDyFwy2m9AtBwiEcHE5p5lz2h7E3k1PISVnBS+gqcc+K90nsVrmZPdAgMBAAECggEAQwj7EFMRw1rnn3nRG20KJJ8sCOESUT+cLMsJOiyUBEXi9shX9kd+EIgjYzIe+jTFtqJjqEd2PYuXe+qp3ah9sLBitV/+kWgHixv39gngjkSUAlwEGNvrHsBD+z/7ynAEHwt9oVIVUTNnqutuKrYZh/y4ttVvbEWQGQPWMQrIGqAIi1uwYC0b56Wybo2S4usGcMrURSuSdfPvs+0P59DwjbONr5U1PStjXd8/81xVqT76PjhrIBcUVcyw3Lw+qUqOLY7eTRZr9thVHuD/sDnhC8dFlgoFc5k9cC2NFyLVCW81Qx6JQIVN22J838Dmkr+iTEvETi/3rlLPFLeSFWa+YQKBgQDvPyKbjYjdtrpZkp6Qx2PVLqsu4k0S82x1PPiEt0ugYc9JPZE0SIIz+F9gpDVPSEgRU3kVQKuMt5San3CYaTUBK+/12On9Ngyj0RUmLwTXkHm5Hxfeq5IGPoj82EIsrQkz18AynrO1k/xqzGqiiIQg8c317dyViyKdthm+H/MTiQKBgQC5X/OoNSdCRwR4twdHYpIzVhpc0qfw5iqUaeYrhOPiZsyl11bw/ZY03zBKVEpFGYF6L1uXRF4+oupUMQ6HVZdoEj+o6bwxn5a5j8csGISfDPvvh0R3vulW8FSrmEGKvljRT4otsPB9ivaxyQzbXMFiKBcAHdKPDEtMgx+8pzGktQKBgQDnilCx/r5Q5QFgovd24qGJtgrR6w3wo0l6BTH3L3WvmDmGhmE7Gll4do3H9+PoAOpOalyq5tWf8COPqXLM7jTwF1e6tN1vw5lkCXvQYnzrKtXDBYaSZ7WA+ZCWM62Hk0dutwU5eEIwHzKRIZiqCDjnsV1E/5kV2Xv86jnruPwFEQKBgQCFP1dhXXOuzGWGb+mngk8c35NrjXByVL/elpCAT7GOl94ah0NTTrSdpKCNjTElokPTlB7+CILF+0z835ZV6Uw/3n4r6HcCJHivQY4lQCp0OV0Ou+iXOn93oqD6ByN1m4U0Tzw/w6PaWf0hpia7t1y6NZ7y0nz7nhayFL7HWFby0QKBgG+l5+LSz67DXrZYtPZ22vbQ5vWbYM3llDa4jrjFaWoT5Cm5D4QedH7rXiIhKwO95dTYPWQL0kZ9dZnK1wvDIOtYtiGGu0K1ykI13WCEE/UQxQqRlFnpTwWetw61bVrF9mxfDTJkwGvEiahcxXtBK7JVgKVCJquGJjXezjroWIWI";
    public static final String APPID = "2021002147646117";

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    //同步获取结果
                    String resultInfo = payResult.getResult();
                    Log.i("Pay", "Pay:" + resultInfo);
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(FoodItem.this, "Payment successful", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(FoodItem.this, "Payment failed", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item);
        foodItemImageView = findViewById(R.id.foodItemImageView);
        foodItemTitleTextView = findViewById(R.id.foodItemTitleTextView);
        foodItemDescriptionTextView = findViewById(R.id.foodItemDescriptionTextView);
        foodItemDateTextView = findViewById(R.id.foodItemDateTextView);
        foodItemTimeTextView = findViewById(R.id.foodItemTimeTextView);
        foodItemQuantityTextView = findViewById(R.id.foodItemQuantityTextView);
        foodItemLocationTextView = findViewById(R.id.foodItemLocationTextView);
        foodItem = new Food();
        db = new DatabaseHelper(this);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        if (position == 0){
            Toast.makeText(FoodItem.this, "Error", Toast.LENGTH_LONG).show();
        }
        else{
            foodItem = db.fetchFood(position);
            foodItemImageView.setImageDrawable(transformToDrawable(foodItem.getFoodImage()));
            foodItemTitleTextView.setText(foodItem.getFoodTitle());
            foodItemDescriptionTextView.setText(foodItem.getFoodDescription());
            foodItemDateTextView.setText(foodItem.getFoodDate());
            foodItemTimeTextView.setText(foodItem.getFoodTime());
            foodItemQuantityTextView.setText(foodItem.getFoodQuantity());
            foodItemLocationTextView.setText(foodItem.getFoodLocation());
        }
    }

    public void clickAddFoodToCart(View view) {
        long result = db.insertFood(foodItem, 2);
        if (result != 0){
            Toast.makeText(FoodItem.this, "Added successfully!", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(FoodItem.this, "Error! The result is " + result, Toast.LENGTH_LONG).show();
        }
    }

    public void clickBuy(View view) {
        //秘钥验证的类型 true:RSA2 false:RSA
        boolean rsa = false;
        //构造支付订单参数列表
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa);
        //构造支付订单参数信息
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);
        //对支付参数信息进行签名
        String sign = OrderInfoUtil2_0.getSign(params, RSA_PRIVATE, rsa);
        //订单信息
        final String orderInfo = orderParam + "&" + sign;
        //异步处理
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                //新建任务
                PayTask alipay = new PayTask(FoodItem.this);
                //获取支付结果
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    public Drawable transformToDrawable(byte[] image){
        Bitmap bitmap = BitmapFactory.decodeByteArray(image,0, image.length, null);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
        Drawable drawable = bitmapDrawable;
        return drawable;
    }
}