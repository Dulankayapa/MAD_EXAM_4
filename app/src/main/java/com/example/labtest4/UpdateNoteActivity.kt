package com.example.labtest4

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.labtest4.databinding.ActivityUpdateNoteBinding


class UpdateNoteActivity: AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db: NotesDatabaseHelper
    private var noteld: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NotesDatabaseHelper(this)

        noteld = intent.getIntExtra("note_id", -1)
        if (noteld == -1) {
            finish()
            return

        }
        val note = db.getNoteByID (noteld)
        binding.updatetitleEditText.setText(note.title)
        binding.updatecontentEditText.setText(note.content)

        binding.updatesaveButton.setOnClickListener{
            val newTitle = binding.updatetitleEditText.text.toString()
            val newContent = binding.updatecontentEditText.text.toString()
            val updatedNote = Note (noteld, newTitle, newContent)
            db.updateNote (updatedNote)
            finish()
            Toast.makeText(this,  "Changes Saved", Toast.LENGTH_SHORT).show()
        }


    }
}