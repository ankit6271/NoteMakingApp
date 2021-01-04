package com.example.notemakingapp;

import android.content.Intent;
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
    EditText editTextTitle, editTextDesc;
    NumberPicker numberPicker;
    Button button;
    NoteViewModel viewModel;
    String s;
    Intent i;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnote);

        editTextTitle = (EditText) findViewById(R.id.TitleEdit);
        editTextDesc = (EditText) findViewById(R.id.DescEdit);
        button = (Button) findViewById(R.id.Button);
        button.setOnClickListener(this);
        numberPicker = (NumberPicker) findViewById(R.id.NumP);
        numberPicker.setMaxValue(10);
        numberPicker.setMinValue(1);

        i= getIntent();
        s = i.getStringExtra("nameAdd");
        assert s != null;
        if (s.equals("EDIT")) {
            setTitle("Edit");
            i.getStringExtra("Prior");
            i.getStringExtra("Desc");
            editTextTitle.setText(i.getStringExtra("Title"));
            editTextDesc.setText(i.getStringExtra("Desc"));
            numberPicker.setValue(i.getIntExtra("Prior", 1));
        }
        else{
            setTitle("Add");
        }

        viewModel = ViewModelProviders.of(AddActivity.this).get(NoteViewModel.class);
    }

    @Override
    public void onClick(View view) {
        String title = editTextTitle.getText().toString();
        String desc = editTextDesc.getText().toString();
        int value = numberPicker.getValue();
        if (s.equals("EDIT")) {
            if (title.trim().isEmpty() && desc.trim().isEmpty()) {
                Toast.makeText(this, "Pease enter the title and desc", Toast.LENGTH_LONG).show();
            } else {
                Note note=new Note(title,desc,value);
                //Note id which needs to be updated
                note.setId(i.getIntExtra("Id",1));
                viewModel.update(note);
                finish();
            }
        }
        else {
            if (title.trim().isEmpty() && desc.trim().isEmpty()) {
                Toast.makeText(this, "Pease enter the title and desc", Toast.LENGTH_LONG).show();
            } else {
                viewModel.insert(new Note(title, desc, value));
                finish();
            }
        }
    }
}
