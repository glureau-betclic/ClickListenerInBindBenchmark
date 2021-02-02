package com.glureau.clicklistenerinbindbenchmark

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.glureau.clicklistenerinbindbenchmark.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding.root)
        val adapter = MessagesAdapter()
        binding.recycler.adapter = adapter
        adapter.submitList(
                listOf(
                        ImMessage("1", true),
                        ImMessage("1", true),
                        ImMessage("1", true),
                        ImMessage("2", false),
                        ImMessage("1", true),
                        ImMessage("1", true),
                        ImMessage("2", false),
                        ImMessage("2", false),
                        ImMessage("2", false),
                        ImMessage("1", true),
                        ImMessage("1", true),
                        ImMessage("2", false),
                        ImMessage("1", true),
                        ImMessage("1", true),
                        ImMessage("1", true),
                        ImMessage("1", true),
                        ImMessage("2", false),
                        ImMessage("1", true),
                        ImMessage("1", true),
                        ImMessage("2", false),
                        ImMessage("2", false),
                        ImMessage("2", false),
                        ImMessage("1", true),
                        ImMessage("1", true),
                        ImMessage("2", false),
                        ImMessage("1", true),
                        ImMessage("1", true),
                        ImMessage("1", true),
                        ImMessage("1", true),
                        ImMessage("2", false),
                        ImMessage("1", true),
                        ImMessage("1", true),
                        ImMessage("2", false),
                        ImMessage("2", false),
                        ImMessage("2", false),
                        ImMessage("1", true),
                        ImMessage("1", true),
                        ImMessage("2", false),
                        ImMessage("1", true),
                )
        )
    }
}