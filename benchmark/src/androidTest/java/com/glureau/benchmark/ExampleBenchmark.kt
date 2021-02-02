package com.glureau.benchmark

import android.content.Context
import android.text.PrecomputedText
import android.view.LayoutInflater
import android.view.View
import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.glureau.benchmark.databinding.ItemBinding
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Benchmark, which will execute on an Android device.
 *
 * The body of [BenchmarkRule.measureRepeated] is measured in a loop, and Studio will
 * output the result. Modify your code to see how it affects performance.
 */
@RunWith(AndroidJUnit4::class)
class ExampleBenchmark {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val inflater = LayoutInflater.from(context)
    private val inflatedView = ItemBinding.inflate(inflater)

    @Test
    fun set_text() {
        val msg1 = "msg1"

        benchmarkRule.measureRepeated {
            inflatedView.message.text = msg1
        }
    }

    @Test
    fun set_text_optimized() {
        val msg1 = "msg1"

        val params: PrecomputedText.Params = inflatedView.message.textMetricsParams
        val precomputedText = PrecomputedText.create(msg1, params)

        Thread.sleep(1000)

        benchmarkRule.measureRepeated {
            inflatedView.message.text = precomputedText
        }
    }

    @Test
    fun set_new_click_listener() {
        val action: (Int) -> Unit = {}

        benchmarkRule.measureRepeated {
            inflatedView.message.setOnClickListener {
                action(it.id)
            }
        }
    }


    @Test
    fun reuse_click_listener() {
        val action: (Int) -> Unit = {}
        val reusedClickListener = View.OnClickListener {
            action(it.id)
        }

        benchmarkRule.measureRepeated {
            inflatedView.message.setOnClickListener(reusedClickListener)
        }
    }

    @Test
    fun bind_on_constructor() {
        val action: (Int) -> Unit = {}
        val reusedClickListener = View.OnClickListener {
            action(it.id)
        }
        inflatedView.message.setOnClickListener(reusedClickListener)

        benchmarkRule.measureRepeated {
        }
    }

}