package br.com.trainning.pdv.ui;

/**
 * Created by android on 05/03/2016.
 */

import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by elcio on 23/11/15.
 */
public class BaseActivity extends AppCompatActivity {


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }
}