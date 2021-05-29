package com.example.ALGOPA.view.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ALGOPA.R;
import com.example.ALGOPA.services.model.Users;
import com.example.ALGOPA.viewModel.DatabaseViewModel;
import com.example.ALGOPA.viewModel.SignInViewModel;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignupActivity extends AppCompatActivity {
    ArrayAdapter<CharSequence> adspin1, adspin2;
    String choice_do="";
    String choice_se="";
    EditText et_usernameSignIn;
    EditText et_emailIdSignIn;
    EditText et_pwdSignIn;
    Button btn_signIn;
    TextView textToLogin;
    SignInViewModel signInViewModel;
    DatabaseViewModel databaseViewModel;
    String emailId;
    String pwd;
    String userName;
    Context context;
    String userId;
    String imageUrl;
    String timeStamp;
    String member;
    FirebaseUser currentUser;
    FrameLayout progressBarSignInFrame;
    Users users;
    RadioButton normal, pro;
    private FirebaseAuth firebaseAuth;

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
        listeners();

        final Spinner spin1 = (Spinner)findViewById(R.id.spinner);
        final Spinner spin2 = (Spinner)findViewById(R.id.spinner2);

        adspin1 = ArrayAdapter.createFromResource(this,R.array.spinner_do, android.R.layout.simple_spinner_dropdown_item);
        adspin1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin1.setAdapter(adspin1);
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long I) {
                if(adspin1.getItem(i).equals("없음")){
                    choice_do = "없음";
                    adspin2 = ArrayAdapter.createFromResource(SignupActivity.this, R.array.spinner_do_false, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long I) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }else if (adspin1.getItem(i).equals("인문대학")){
                    choice_do = "인문대학";
                    adspin2 = ArrayAdapter.createFromResource(SignupActivity.this,R.array.spinner_do_College_of_Humanities, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int i, long I) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }else if (adspin1.getItem(i).equals("사회과학대학")){
                    choice_do = "사회과학대학";
                    adspin2 = ArrayAdapter.createFromResource(SignupActivity.this,R.array.spinner_do_Social, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int i, long I) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }else if (adspin1.getItem(i).equals("자연과학대학")){
                    choice_do = "자연과학대학";
                    adspin2 = ArrayAdapter.createFromResource(SignupActivity.this,R.array.spinner_do_Natural_science, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int i, long I) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }else if (adspin1.getItem(i).equals("경영대학")){
                    choice_do = "경영대학";
                    adspin2 = ArrayAdapter.createFromResource(SignupActivity.this,R.array.spinner_do_operation, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int i, long I) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }else if (adspin1.getItem(i).equals("공과대학")){
                    choice_do = "공과대학";
                    adspin2 = ArrayAdapter.createFromResource(SignupActivity.this,R.array.spinner_do_College_of_Engineering, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int i, long I) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }else if (adspin1.getItem(i).equals("농업생명과학대학")){
                    choice_do = "농업생명과학대학";
                    adspin2 = ArrayAdapter.createFromResource(SignupActivity.this,R.array.spinner_do_Agriculture, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int i, long I) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }else if (adspin1.getItem(i).equals("법과대학")){
                    choice_do = "법과대학";
                    adspin2 = ArrayAdapter.createFromResource(SignupActivity.this,R.array.spinner_do_method, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int i, long I) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }else if (adspin1.getItem(i).equals("사범대학")){
                    choice_do = "사범대학";
                    adspin2 = ArrayAdapter.createFromResource(SignupActivity.this,R.array.spinner_do_Normal_University, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int i, long I) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }else if (adspin1.getItem(i).equals("수의과학대학")){
                    choice_do = "수의과학대학";
                    adspin2 = ArrayAdapter.createFromResource(SignupActivity.this,R.array.spinner_do_Veterinary, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int i, long I) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }else if (adspin1.getItem(i).equals("의과대학")){
                    choice_do = "의과대학";
                    adspin2 = ArrayAdapter.createFromResource(SignupActivity.this,R.array.spinner_do_Pre_medicine, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int i, long I) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }else if (adspin1.getItem(i).equals("해양대학")){
                    choice_do = "해양대학";
                    adspin2 = ArrayAdapter.createFromResource(SignupActivity.this,R.array.spinner_do_Department_of_Oceanography, android.R.layout.simple_spinner_dropdown_item);
                    adspin2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(adspin2);
                    spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int i, long I) {
                            choice_se = adspin2.getItem(i).toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void listeners() {

        btn_signIn.setOnClickListener(new View.OnClickListener() {
            final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

            @Override
            public void onClick(View v) {
                et_usernameSignIn.clearFocus();
                et_emailIdSignIn.clearFocus();
                et_pwdSignIn.clearFocus();
                v.startAnimation(buttonClick);

                emailId = et_emailIdSignIn.getText().toString();
                pwd = et_pwdSignIn.getText().toString();
                userName = et_usernameSignIn.getText().toString();
                if ((pwd.isEmpty() && emailId.isEmpty() && userName.isEmpty())) {
                    Toast.makeText(SignupActivity.this, "Fields are empty!", Toast.LENGTH_SHORT).show();
                    et_usernameSignIn.requestFocus();
                } else if (userName.isEmpty()) {
                    et_usernameSignIn.setError("Please enter a username.");
                    et_usernameSignIn.requestFocus();
                } else if (emailId.isEmpty()) {
                    et_emailIdSignIn.setError("Please enter your Email Id.");
                    et_emailIdSignIn.requestFocus();
                } else if (pwd.isEmpty()) {
                    et_pwdSignIn.setError("Please set your password.");
                    et_pwdSignIn.requestFocus();
                } else {
                    progressBarSignInFrame.setVisibility(View.VISIBLE);
                    et_usernameSignIn.setClickable(false);
                    et_emailIdSignIn.setClickable(false);
                    et_pwdSignIn.setClickable(false);
                    textToLogin.setClickable(false);
                    dismissKeyboard();
                    signInUsers();
                }
            }
        });

        textToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                finish();
            }
        });

        RadioGroup radios = findViewById(R.id.radios);
        radios.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.normal_member) {
                    member = "normal member";
                } else if (checkedId == R.id.pro_member) {
                    member = "pro member";
                }
            }
        });
    }

    public void signInUsers() {
        signInViewModel.userSignIn(userName, emailId, pwd);
        signInViewModel.signInUser.observe(this, new Observer<Task>() {
            @Override
            public void onChanged(Task task) {
                if (!task.isSuccessful()) {
                    et_usernameSignIn.setClickable(true);
                    et_emailIdSignIn.setClickable(true);
                    et_pwdSignIn.setClickable(true);
                    textToLogin.setClickable(true);

                    progressBarSignInFrame.setVisibility(View.GONE);
                    et_emailIdSignIn.setText("");
                    et_pwdSignIn.setText("");
                    et_usernameSignIn.setText("");
                    et_usernameSignIn.requestFocus();

                    try {
                        throw Objects.requireNonNull(task.getException());
                    } catch (FirebaseAuthUserCollisionException existEmail) {
                        Toast.makeText(context, "Email Id already exists.", Toast.LENGTH_SHORT).show();
                    } catch (FirebaseAuthWeakPasswordException weakPassword) {
                        Toast.makeText(context, "Password length should be more then six characters.", Toast.LENGTH_SHORT).show();
                    } catch (FirebaseAuthInvalidCredentialsException malformedEmail) {
                        Toast.makeText(context, "Invalid credentials, please try again.", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(context, "SignUp unsuccessful. Try again.", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    getUserSession();
                    addUserInDatabase(userName, emailId, member);
                    Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

        });
    }

    private void addUserInDatabase(String userName, String email, String userMember) {
        long tsLong = System.currentTimeMillis();
        timeStamp = Long.toString(tsLong);
        imageUrl = "default";
        userId = currentUser.getUid();
        assert userMember != null;
        databaseViewModel.addUserInDatabase(userId, userName, email, timeStamp, imageUrl, userMember);
        databaseViewModel.successAddUserDb.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean)
                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(context, "ERROR WHILE ADDING DATA IN DATABASE.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getUserSession() {
        signInViewModel.getUserFirebaseSession();
        signInViewModel.userFirebaseSession.observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                currentUser = firebaseUser;
            }
        });

    }

    public void dismissKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    }


    private void init() {
        et_usernameSignIn = findViewById(R.id.et_signin_username);
        et_emailIdSignIn = findViewById(R.id.et_signin_email);
        et_pwdSignIn = findViewById(R.id.et_signin_password);
        btn_signIn = findViewById(R.id.btn_signin);
        textToLogin = findViewById(R.id.text_to_login);
        context = SignupActivity.this;
        signInViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(getApplication()))
                .get(SignInViewModel.class);
        databaseViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(getApplication()))
                .get(DatabaseViewModel.class);
        progressBarSignInFrame = findViewById(R.id.progress_bar_signIn);
        normal = findViewById(R.id.normal_member);
        pro = findViewById(R.id.pro_member);
    }

}
