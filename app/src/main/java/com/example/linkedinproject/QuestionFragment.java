package com.example.linkedinproject;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.linkedinproject.databinding.FragmentQuestionBinding;
import com.example.linkedinproject.model.Answer;
import com.example.linkedinproject.model.Questions;
import com.example.linkedinproject.utils.ReadAssetFile;
import com.example.linkedinproject.viewmodel.VmQuestion;
import com.google.android.gms.plus.PlusOneButton;
import com.google.gson.Gson;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

/**
 * A fragment with a Google +1 button.
 */
public class QuestionFragment extends Fragment {


    private VmQuestion vmQuestion;
    private FragmentQuestionBinding fragmentQuestionBinding;
    private String answer;


    public QuestionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InputStream stream = ReadAssetFile.getFileData(getContext());
        InputStreamReader reader = new InputStreamReader(stream);
        Questions questions = new Gson().fromJson(reader, Questions.class);

        QAStoreManager.getInstance().saveParseData(questions);
        vmQuestion = new VmQuestion();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fragmentQuestionBinding
                = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false);

        fragmentQuestionBinding.setVm(vmQuestion);
        return fragmentQuestionBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupOnClickListener();
    }

    void setupOnClickListener() {

        RxTextView.textChanges(fragmentQuestionBinding.answer)
                .skipInitialValue()
                .debounce(100, TimeUnit.MILLISECONDS)
                .map(CharSequence::toString)
                .filter(answer -> answer.length() > 3)
                .doOnNext(answer -> {
                    QuestionFragment.this.answer = answer;
                })
                .subscribe();


        fragmentQuestionBinding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentQuestionBinding.answer.setText("");
                vmQuestion.saveAnswer(new Answer(fragmentQuestionBinding.question.getText().toString(), answer));
                vmQuestion.setNextQuestion();
            }
        });

        fragmentQuestionBinding.finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // finish part
                if (getActivity() instanceof MainActivity) {
                    MainActivity activity = (MainActivity) getActivity();
                    activity.loadDetailFragment();
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
