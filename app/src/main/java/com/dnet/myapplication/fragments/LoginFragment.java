package com.dnet.myapplication.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dnet.myapplication.R;
import com.dnet.myapplication.models.LoginRespone;
import com.dnet.myapplication.models.question.QuestionAnsResult;
import com.dnet.myapplication.preference.SharedPreference;
import com.dnet.myapplication.task.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment {



    Button buttonLogin;
    EditText phoneNo, password;
    ProgressDialog progressDialog;

    public LoginFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false); // set cancelable to false
        progressDialog.setMessage("Please Wait"); // set message
        NavController navController = Navigation.findNavController(view);
        buttonLogin = view.findViewById(R.id.login_button);
        phoneNo = view.findViewById(R.id.editTextPhone);
        password = view.findViewById(R.id.editTextTextPassword);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                navController.navigate(R.id.action_loginFragment_to_dashboardFragment);
                signUp();
            }
        });
    }

    private void signUp() {
        // display a progress dialog

        progressDialog.show(); // show progress dialog

        (Api.getClient().registration(phoneNo.getText().toString().trim(),
                password.getText().toString().trim())).enqueue(new Callback<LoginRespone>() {
            @Override
            public void onResponse(Call<LoginRespone> call, Response<LoginRespone> response) {
                int code = response.code();
                progressDialog.dismiss();
                if (!response.body().getData().getToken().equals(null)) {
                    Toast.makeText(getContext(), ""+response.body().getData().getToken(), Toast.LENGTH_SHORT).show();

                    SharedPreference prefrence = SharedPreference.getInstance(getContext());
                    prefrence.saveData("userToken", response.body().getData().getToken());
                    GetQuesAns();
                }

            }

            @Override
            public void onFailure(Call<LoginRespone> call, Throwable t) {
                Log.d("response", t.getStackTrace().toString());
                progressDialog.dismiss();

            }
        });
    }

    private void GetQuesAns() {
        // display a progress dialog
        SharedPreference yourPrefrence = SharedPreference.getInstance(getContext());
        progressDialog.show(); // show progress dialog

        (Api.getClient().GetQuestionAnswer(yourPrefrence.getData("userToken"),"")).enqueue(new Callback<QuestionAnsResult>() {
            @Override
            public void onResponse(Call<QuestionAnsResult> call, Response<QuestionAnsResult> response) {
                Log.d("RESPONSE", "resposne code "+response.code());
            }

            @Override
            public void onFailure(Call<QuestionAnsResult> call, Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();

            }
        });


    }
}