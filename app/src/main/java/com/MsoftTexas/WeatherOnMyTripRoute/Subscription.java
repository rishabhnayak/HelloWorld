package com.MsoftTexas.WeatherOnMyTripRoute;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.SkuDetails;
import com.anjlab.android.iab.v3.TransactionDetails;

public class Subscription extends AppCompatActivity {
  //  private static final String ACTIVITY_NUMBER = "activity_num";
    private static final String LOG_TAG = "iabv3";

    // PRODUCT & SUBSCRIPTION IDS
//    private static final String PRODUCT_ID = "10001";
  //  private static final String SUBSCRIPTION_ID1 = "infinite_query_monthly";
    private static final String SUBSCRIPTION_ID1 = "trialpack_10rs";
    private static final String SUBSCRIPTION_ID2 = "infinite_query_quarterly";
    private static final String SUBSCRIPTION_ID3 = "infinite_query_yearly";
  //  private static final String SUBSCRIPTION_ID4 = "monthly_001";
    private final String LICENSE_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhkWatLBkcw3ELvpucag5CAFHjVfT8mR+ZpjfN/f59cMxpprIC3rFGgBNMbY+IrS5wgNKuPY6JqTOfiDB9oDJl/XGyVWnXkA9CQtCBEyobtpm4QKIxqBi4iy1d+kGvzrni/6Hhq3CLuBn6EVBs49Y2tktABnO67Niwh+xsVdPBND+zfjXAc3lB4TEGPO6RXTcZYtQeCP2k9d5wOb/lwHVN0vFd9a3sBd2vmBHZC+2ocH+jPs5rVO+S1flBdvjlpDhpdEBY0ODrR6gA9Vuj0tOtAdLqx1Bg8+LwRBku/1TPA3BzieCWqZTujAuLVYsQiglggw4lc2+h7f+fnmnam+y7wIDAQAB"; // PUT YOUR MERCHANT KEY HERE;
    // put your Google merchant id here (as stated in public profile of your Payments Merchant Center)
    // if filled library will provide protection against Freedom alike Play Market simulators
    private static final String MERCHANT_ID=null;

    private BillingProcessor bp;
    private boolean readyToPurchase = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

     //   TextView title = (TextView)findViewById(R.id.titleTextView);
      //  title.setText(String.format(getString(R.string.title), getIntent().getIntExtra(ACTIVITY_NUMBER, 1)));

        if(!BillingProcessor.isIabServiceAvailable(this)) {
            showToast("In-app billing service is unavailable, please upgrade Android Market/Play to version >= 3.9.16");
        }

        bp = new BillingProcessor(this, LICENSE_KEY, MERCHANT_ID, new BillingProcessor.IBillingHandler() {
            @Override
            public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
                showToast("onProductPurchased: " + productId);
                updateTextViews();
            }
            @Override
            public void onBillingError(int errorCode, @Nullable Throwable error) {
                showToast("onBillingError: " + Integer.toString(errorCode));
           //     System.out.println(error.fillInStackTrace());
                System.out.println(errorCode);
            }
            @Override
            public void onBillingInitialized() {
                showToast("onBillingInitialized");
                readyToPurchase = true;
                updateTextViews();
            }
            @Override
            public void onPurchaseHistoryRestored() {
                showToast("onPurchaseHistoryRestored");
                for(String sku : bp.listOwnedProducts())
                    Log.d(LOG_TAG, "Owned Managed Product: " + sku);
                for(String sku : bp.listOwnedSubscriptions())
                    Log.d(LOG_TAG, "Owned Subscription: " + sku);
                updateTextViews();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        updateTextViews();
    }

    @Override
    public void onDestroy() {
        if (bp != null)
            bp.release();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!bp.handleActivityResult(requestCode, resultCode, data))
            super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateTextViews() {
    //    TextView text = (TextView)findViewById(R.id.productIdTextView);
    //    text.setText(String.format("%s is%s purchased", PRODUCT_ID, bp.isPurchased(PRODUCT_ID) ? "" : " not"));
      TextView  text = (TextView)findViewById(R.id.subscriptionIdTextView1);
        text.setText(String.format("%s is%s Subscribed (Price-$1)", SUBSCRIPTION_ID1, bp.isSubscribed(SUBSCRIPTION_ID1) ? "" : " not"));
        text = (TextView)findViewById(R.id.subscriptionIdTextView2);
        text.setText(String.format("%s is%s Subscribed (Price-$3)", SUBSCRIPTION_ID2, bp.isSubscribed(SUBSCRIPTION_ID2) ? "" : " not"));
        text = (TextView)findViewById(R.id.subscriptionIdTextView3);
        text.setText(String.format("%s is%s Subscribed (Price-$10)", SUBSCRIPTION_ID3, bp.isSubscribed(SUBSCRIPTION_ID3) ? "" : " not"));
    //    text = (TextView)findViewById(R.id.subscriptionIdTextView4);
    //    text.setText(String.format("%s is%s Subscribed", SUBSCRIPTION_ID4, bp.isSubscribed(SUBSCRIPTION_ID4) ? "" : " not"));
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void onClick(View v) {
        if (!readyToPurchase) {
            showToast("Billing not initialized.");
            return;
        }
        switch (v.getId()) {
//            case R.id.purchaseButton:
//                bp.purchase(this,PRODUCT_ID);
//                break;
//            case R.id.consumeButton:
//                Boolean consumed = bp.consumePurchase(PRODUCT_ID);
//                updateTextViews();
//                if (consumed)
//                    showToast("Successfully consumed");
//                break;
//            case R.id.productDetailsButton:
//                SkuDetails sku = bp.getPurchaseListingDetails(PRODUCT_ID);
//                showToast(sku != null ? sku.toString() : "Failed to load SKU details");
//                break;
            case R.id.subscribeButton1:
                bp.subscribe(this,SUBSCRIPTION_ID1);
                break;
            case R.id.updateSubscriptionsButton1:
                if (bp.loadOwnedPurchasesFromGoogle()) {
                    showToast("Subscriptions updated.");
                    updateTextViews();
                }
                break;
            case R.id.subsDetailsButton1:
                SkuDetails subs = bp.getSubscriptionListingDetails(SUBSCRIPTION_ID1);
                showToast(subs != null ? subs.toString() : "Failed to load subscription details");
                break;

            case R.id.subscribeButton2:
                bp.subscribe(this,SUBSCRIPTION_ID2);
                break;
            case R.id.updateSubscriptionsButton2:
                if (bp.loadOwnedPurchasesFromGoogle()) {
                    showToast("Subscriptions updated.");
                    updateTextViews();
                }
                break;
            case R.id.subsDetailsButton2:
                subs = bp.getSubscriptionListingDetails(SUBSCRIPTION_ID2);
                showToast(subs != null ? subs.toString() : "Failed to load subscription details");
                break;
            case R.id.subscribeButton3:
                bp.subscribe(this,SUBSCRIPTION_ID3);
                break;
            case R.id.updateSubscriptionsButton3:
                if (bp.loadOwnedPurchasesFromGoogle()) {
                    showToast("Subscriptions updated.");
                    updateTextViews();
                }
                break;
            case R.id.subsDetailsButton3:
                 subs = bp.getSubscriptionListingDetails(SUBSCRIPTION_ID3);
                showToast(subs != null ? subs.toString() : "Failed to load subscription details");
                break;
//            case R.id.subscribeButton4:
//                bp.subscribe(this,SUBSCRIPTION_ID4);
//                break;
//            case R.id.updateSubscriptionsButton4:
//                if (bp.loadOwnedPurchasesFromGoogle()) {
//                    showToast("Subscriptions updated.");
//                    updateTextViews();
//                }
//                break;
//            case R.id.subsDetailsButton4:
//                subs = bp.getSubscriptionListingDetails(SUBSCRIPTION_ID4);
//                showToast(subs != null ? subs.toString() : "Failed to load subscription details");
//                break;
//            case R.id.launchMoreButton:
//                startActivity(new Intent(this, MainActivity.class).putExtra(ACTIVITY_NUMBER, getIntent().getIntExtra(ACTIVITY_NUMBER, 1) + 1));
//                break;
            default:
                break;
        }
    }

}
