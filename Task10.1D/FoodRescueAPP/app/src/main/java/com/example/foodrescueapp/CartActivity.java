package com.example.foodrescueapp;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.example.foodrescueapp.data.DatabaseHelper;
import com.example.foodrescueapp.model.Food;
import com.example.foodrescueapp.util.OrderInfoUtil2_0;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartActivity extends AppCompatActivity {
    RecyclerView cartRecyclerView;
    CartRecyclerViewAdapter cartRecyclerViewAdapter;
    List<Food> cartFoodList = new ArrayList<>();
    TextView cartTotalTextView;
    DatabaseHelper db;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private static final String RSA2_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCtPkwmYfaOKj9cO5EMRFbLL5rO0Wxp78Lorr7JxxDgnRIwGx9uoECHGAPo/6DTypOF2eJTzNLLZKCq6RJtO1zBR8JwJIsfrJWPMXI5s5tVchtjH5eLn+UiPwpxh0YdQOLtSjxQ4OdUokvq/kO4C5dvvpdcR7znyr4I5zVr9L7ZzFQMlxWyC9GpTnbo8pIzP2MuKLMl7dVwM6s2HbXEFVkofirlaJpmdM4By4JqPfO3G1wtbGAgJooJkwTBWFKcCIq4HUQWF4fQmo0rnuDvRY6JbyveRevjqNpDyFwy2m9AtBwiEcHE5p5lz2h7E3k1PISVnBS+gqcc+K90nsVrmZPdAgMBAAECggEAQwj7EFMRw1rnn3nRG20KJJ8sCOESUT+cLMsJOiyUBEXi9shX9kd+EIgjYzIe+jTFtqJjqEd2PYuXe+qp3ah9sLBitV/+kWgHixv39gngjkSUAlwEGNvrHsBD+z/7ynAEHwt9oVIVUTNnqutuKrYZh/y4ttVvbEWQGQPWMQrIGqAIi1uwYC0b56Wybo2S4usGcMrURSuSdfPvs+0P59DwjbONr5U1PStjXd8/81xVqT76PjhrIBcUVcyw3Lw+qUqOLY7eTRZr9thVHuD/sDnhC8dFlgoFc5k9cC2NFyLVCW81Qx6JQIVN22J838Dmkr+iTEvETi/3rlLPFLeSFWa+YQKBgQDvPyKbjYjdtrpZkp6Qx2PVLqsu4k0S82x1PPiEt0ugYc9JPZE0SIIz+F9gpDVPSEgRU3kVQKuMt5San3CYaTUBK+/12On9Ngyj0RUmLwTXkHm5Hxfeq5IGPoj82EIsrQkz18AynrO1k/xqzGqiiIQg8c317dyViyKdthm+H/MTiQKBgQC5X/OoNSdCRwR4twdHYpIzVhpc0qfw5iqUaeYrhOPiZsyl11bw/ZY03zBKVEpFGYF6L1uXRF4+oupUMQ6HVZdoEj+o6bwxn5a5j8csGISfDPvvh0R3vulW8FSrmEGKvljRT4otsPB9ivaxyQzbXMFiKBcAHdKPDEtMgx+8pzGktQKBgQDnilCx/r5Q5QFgovd24qGJtgrR6w3wo0l6BTH3L3WvmDmGhmE7Gll4do3H9+PoAOpOalyq5tWf8COPqXLM7jTwF1e6tN1vw5lkCXvQYnzrKtXDBYaSZ7WA+ZCWM62Hk0dutwU5eEIwHzKRIZiqCDjnsV1E/5kV2Xv86jnruPwFEQKBgQCFP1dhXXOuzGWGb+mngk8c35NrjXByVL/elpCAT7GOl94ah0NTTrSdpKCNjTElokPTlB7+CILF+0z835ZV6Uw/3n4r6HcCJHivQY4lQCp0OV0Ou+iXOn93oqD6ByN1m4U0Tzw/w6PaWf0hpia7t1y6NZ7y0nz7nhayFL7HWFby0QKBgG+l5+LSz67DXrZYtPZ22vbQ5vWbYM3llDa4jrjFaWoT5Cm5D4QedH7rXiIhKwO95dTYPWQL0kZ9dZnK1wvDIOtYtiGGu0K1ykI13WCEE/UQxQqRlFnpTwWetw61bVrF9mxfDTJkwGvEiahcxXtBK7JVgKVCJquGJjXezjroWIWI";
    private static final String RSA_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCtPkwmYfaOKj9cO5EMRFbLL5rO0Wxp78Lorr7JxxDgnRIwGx9uoECHGAPo/6DTypOF2eJTzNLLZKCq6RJtO1zBR8JwJIsfrJWPMXI5s5tVchtjH5eLn+UiPwpxh0YdQOLtSjxQ4OdUokvq/kO4C5dvvpdcR7znyr4I5zVr9L7ZzFQMlxWyC9GpTnbo8pIzP2MuKLMl7dVwM6s2HbXEFVkofirlaJpmdM4By4JqPfO3G1wtbGAgJooJkwTBWFKcCIq4HUQWF4fQmo0rnuDvRY6JbyveRevjqNpDyFwy2m9AtBwiEcHE5p5lz2h7E3k1PISVnBS+gqcc+K90nsVrmZPdAgMBAAECggEAQwj7EFMRw1rnn3nRG20KJJ8sCOESUT+cLMsJOiyUBEXi9shX9kd+EIgjYzIe+jTFtqJjqEd2PYuXe+qp3ah9sLBitV/+kWgHixv39gngjkSUAlwEGNvrHsBD+z/7ynAEHwt9oVIVUTNnqutuKrYZh/y4ttVvbEWQGQPWMQrIGqAIi1uwYC0b56Wybo2S4usGcMrURSuSdfPvs+0P59DwjbONr5U1PStjXd8/81xVqT76PjhrIBcUVcyw3Lw+qUqOLY7eTRZr9thVHuD/sDnhC8dFlgoFc5k9cC2NFyLVCW81Qx6JQIVN22J838Dmkr+iTEvETi/3rlLPFLeSFWa+YQKBgQDvPyKbjYjdtrpZkp6Qx2PVLqsu4k0S82x1PPiEt0ugYc9JPZE0SIIz+F9gpDVPSEgRU3kVQKuMt5San3CYaTUBK+/12On9Ngyj0RUmLwTXkHm5Hxfeq5IGPoj82EIsrQkz18AynrO1k/xqzGqiiIQg8c317dyViyKdthm+H/MTiQKBgQC5X/OoNSdCRwR4twdHYpIzVhpc0qfw5iqUaeYrhOPiZsyl11bw/ZY03zBKVEpFGYF6L1uXRF4+oupUMQ6HVZdoEj+o6bwxn5a5j8csGISfDPvvh0R3vulW8FSrmEGKvljRT4otsPB9ivaxyQzbXMFiKBcAHdKPDEtMgx+8pzGktQKBgQDnilCx/r5Q5QFgovd24qGJtgrR6w3wo0l6BTH3L3WvmDmGhmE7Gll4do3H9+PoAOpOalyq5tWf8COPqXLM7jTwF1e6tN1vw5lkCXvQYnzrKtXDBYaSZ7WA+ZCWM62Hk0dutwU5eEIwHzKRIZiqCDjnsV1E/5kV2Xv86jnruPwFEQKBgQCFP1dhXXOuzGWGb+mngk8c35NrjXByVL/elpCAT7GOl94ah0NTTrSdpKCNjTElokPTlB7+CILF+0z835ZV6Uw/3n4r6HcCJHivQY4lQCp0OV0Ou+iXOn93oqD6ByN1m4U0Tzw/w6PaWf0hpia7t1y6NZ7y0nz7nhayFL7HWFby0QKBgG+l5+LSz67DXrZYtPZ22vbQ5vWbYM3llDa4jrjFaWoT5Cm5D4QedH7rXiIhKwO95dTYPWQL0kZ9dZnK1wvDIOtYtiGGu0K1ykI13WCEE/UQxQqRlFnpTwWetw61bVrF9mxfDTJkwGvEiahcxXtBK7JVgKVCJquGJjXezjroWIWI";
    public static final String APPID = "2021000117667794";

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        showAlert(CartActivity.this, "Payment successful" + payResult);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        showAlert(CartActivity.this, "Payment failed" + payResult);
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        showAlert(CartActivity.this, "Payment successful" + authResult);
                    } else {
                        // 其他状态值则为授权失败
                        showAlert(CartActivity.this, "Payment failed" + authResult);
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        cartRecyclerView = findViewById(R.id.cartRecyclerView);
        cartTotalTextView = findViewById(R.id.cartTotalTextView);
        db = new DatabaseHelper(this);
        cartFoodList = db.fetchAllCarts();

        cartRecyclerViewAdapter = new CartRecyclerViewAdapter(cartFoodList, this);
        cartRecyclerView.setAdapter(cartRecyclerViewAdapter);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartTotalTextView.setText((0.01 * cartFoodList.size()) + "");
    }


    public void clickPay(View view) {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            showAlert(this, getString(R.string.error_missing_appid_rsa_private));
            return;
        }
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(CartActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

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

    private static void showAlert(Context ctx, String info) {
        showAlert(ctx, info, null);
    }

    private static void showAlert(Context ctx, String info, DialogInterface.OnDismissListener onDismiss) {
        new AlertDialog.Builder(ctx)
                .setMessage(info)
                .setPositiveButton("Confirm", null)
                .setOnDismissListener(onDismiss)
                .show();
    }
}