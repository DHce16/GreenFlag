package com.example.ryanriley_greenflag;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.regex.Pattern;


public class SignUp extends AppCompatActivity {

    ImageButton btnBack;
    ImageButton btnNext;
    EditText tietUserName;
    ImageView ivTick1;
    ImageView ivTick2;
    ImageView ivTick3;
    TextView tvEWarning;
    EditText tietPassword;
    Pattern PASSWORD_PATTERN =
            Pattern.compile("^"+
                    "(?=.*[a-z])"+
                    "(?=.*[A-Z])"+
                    "(?=.*[0-9])"+
                    ".{8,}"+
                    "$");
    EditText tietSamePass;
    TextView tv_same_warn;
    TextView tv_bad_pass;
    TextView tv_repeat_pass;
    ImageView iv_cross1;
    ImageView iv_cross2;
    ImageView iv_cross3;
    ImageView iv_cross4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnBack = findViewById(R.id.iv_back_button);
        tietUserName = findViewById(R.id.et_email);
        tietPassword =findViewById(R.id.et_password_first);
        tietSamePass = findViewById(R.id.et_password_check);
        btnNext = findViewById(R.id.btnNext);
        btnNext.setEnabled(false);
        btnNext.setClickable(false);
        tv_same_warn = findViewById(R.id.use_another);
        tv_bad_pass = findViewById(R.id.bad_pass);
        tv_repeat_pass = findViewById(R.id.repeat_pass);
        iv_cross1 = findViewById(R.id.x_one);
        iv_cross2 = findViewById(R.id.x_two);
        iv_cross3 = findViewById(R.id.x_three);
        iv_cross4 = findViewById(R.id.x_four);

        btnBack.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        tietUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                btnCheck(emailValidator(tietUserName), validatePassword(tietPassword));
            }
        });

        tietPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                btnCheck(emailValidator(tietUserName), validatePassword(tietPassword));
            }
        });

        tietSamePass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                btnCheck(emailValidator(tietUserName), validatePassword(tietPassword));
            }
        });

        btnNext.setOnClickListener(view -> taken());
    }
        private boolean emailValidator(EditText etMail) {


            String emailToText = etMail.getText().toString();
            tietUserName = findViewById(R.id.et_email);
            ivTick1 = findViewById(R.id.iv_tick4);
            tvEWarning = findViewById(R.id.tv_ewarning);

            tietUserName.setBackgroundResource(R.drawable.green_border);
            tv_same_warn.setVisibility(View.GONE);
            iv_cross2.setVisibility(View.GONE);

            if (!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
                tietUserName.setBackgroundResource(R.drawable.green_border);
                ivTick1.setVisibility(View.VISIBLE);
                tvEWarning.setVisibility(View.GONE);
                iv_cross1.setVisibility(View.GONE);
                return true;
            } else if(emailToText.isEmpty()){
                tietUserName.setBackgroundResource(R.color.white);
                tvEWarning.setVisibility(View.GONE);
                iv_cross1.setVisibility(View.GONE);
                return false;
            } else {
                tietUserName.setBackgroundResource(R.drawable.red_border);
                ivTick1.setVisibility(View.INVISIBLE);
                tvEWarning.setVisibility(View.VISIBLE);
                iv_cross1.setVisibility(View.VISIBLE);
                return false;
            }
        }

        private boolean validatePassword(EditText pass) {
            String passwordInput = pass.getText().toString().trim();
            tietPassword = findViewById(R.id.et_password_first);
            tietSamePass = findViewById(R.id.et_password_check);
            ivTick2 = findViewById(R.id.iv_tick5);
            ivTick3 = findViewById(R.id.iv_tick6);


            if (passwordInput.isEmpty()){
                tietPassword.setBackgroundResource(R.color.white);
                tietSamePass.setBackgroundResource(R.color.white);
                ivTick2.setVisibility(View.INVISIBLE);
                ivTick3.setVisibility(View.INVISIBLE);
                tv_bad_pass.setVisibility(View.GONE);
                iv_cross3.setVisibility(View.GONE);
                return false;
            } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()){
                tietPassword.setBackgroundResource(R.drawable.red_border);
                tietSamePass.setBackgroundResource(R.drawable.red_border);
                ivTick2.setVisibility(View.INVISIBLE);
                ivTick3.setVisibility(View.INVISIBLE);
                tv_bad_pass.setVisibility(View.VISIBLE);
                iv_cross3.setVisibility(View.VISIBLE);
                return false;
            } else {
                tietPassword.setBackgroundResource(R.drawable.green_border);
                tietSamePass.setBackgroundResource(R.drawable.green_border);
                tv_bad_pass.setVisibility(View.GONE);
                iv_cross3.setVisibility(View.GONE);
                return samePassword(tietPassword, tietSamePass);
            }
        }

        private boolean samePassword(EditText pass1, EditText pass2) {
            String password1 = pass1.getText().toString();
            String password2 = pass2.getText().toString();
            tietPassword = findViewById(R.id.et_password_first);

                if (password1.isEmpty() && password2.isEmpty()) {
                    tietPassword.setBackgroundResource(R.color.white);
                    tietSamePass.setBackgroundResource(R.color.white);
                    ivTick2.setVisibility(View.INVISIBLE);
                    ivTick3.setVisibility(View.INVISIBLE);
                    tv_repeat_pass.setVisibility(View.GONE);
                    iv_cross4.setVisibility(View.GONE);
                    return false;
                } else if (password1.equals(password2)) {
                    tietPassword.setBackgroundResource(R.drawable.green_border);
                    tietSamePass.setBackgroundResource(R.drawable.green_border);
                    ivTick2.setVisibility(View.VISIBLE);
                    ivTick3.setVisibility(View.VISIBLE);
                    tv_repeat_pass.setVisibility(View.GONE);
                    iv_cross4.setVisibility(View.GONE);
                    return true;
                } else {
                    tietSamePass.setBackgroundResource(R.drawable.red_border);
                    tietPassword.setBackgroundResource(R.drawable.red_border);
                    ivTick2.setVisibility(View.INVISIBLE);
                    ivTick3.setVisibility(View.INVISIBLE);
                    tv_repeat_pass.setVisibility(View.VISIBLE);
                    iv_cross4.setVisibility(View.VISIBLE);
                    return false;
                }

        }


        private void btnCheck(boolean email, boolean passcheck1){

            if (email && passcheck1) {
                btnNext.setClickable(true);
                btnNext.setEnabled(true);
            }else {
                btnNext.setClickable(false);
                btnNext.setEnabled(false);
            }
        }
        private void inputed(EditText email){
            String emails = email.getText().toString();

            AccountModel accountModel;
            try {
                accountModel = new AccountModel(emails, tietPassword.getText().toString());
            }catch (Exception e) {
                accountModel = new AccountModel("error", "error");
            }
            DataBaseHelper dataBaseHelper = new DataBaseHelper(SignUp.this);

            dataBaseHelper.addOne(accountModel);
        }

        private void taken(){
            DataBaseHelper dataBaseHelper = new DataBaseHelper(SignUp.this);
            Intent intent = new Intent(this, MainActivity.class);
            if (!dataBaseHelper.matches(tietUserName.getText().toString())){
                inputed(tietUserName);
                startActivity(intent);
            }else {
                ivTick1.setVisibility(View.INVISIBLE);
                tietUserName.setBackgroundResource(R.drawable.red_border);
                tv_same_warn.setVisibility(View.VISIBLE);
                iv_cross2.setVisibility(View.VISIBLE);
            }
        }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}