package com.example.notemakingapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.example.notemakingapp.Room.Note;
import com.example.notemakingapp.Room.NoteViewModel;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editTextTitle,editTextDesc;
    NumberPicker numberPicker;
    Button button;
    NoteViewModel viewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnote);

        editTextTitle=(EditText)findViewById(R.id.TitleEdit);
        editTextDesc=(EditText)findViewById(R.id.DescEdit);
        button=(Button)findViewById(R.id.Button);
        button.setOnClickListener(this);
        numberPicker=(NumberPicker)findViewById(R.id.NumP);
        numberPicker.setMaxValue(10);
        numberPicker.setMinValue(1);

        viewModel= ViewModelProviders.of(AddActivity.this).get(NoteViewModel.class);
    }

    @Override
    public void onClick(View view) {
        String title=editTextTitle.getText().toString();
        String desc=editTextDesc.getText().toString();
        int value=numberPicker.getValue();
        if(title.trim().isEmpty() && desc.trim().isEmpty()){
            Toast.makeText(this,"Pease enter the title and desc",Toast.LENGTH_LONG).show();
        }
        else {
            viewModel.insert(new Note(title,desc,value));
            finish();
        }
    }
}
