# ClickListenerInBindBenchmark

Results :

## Huawei P20 Pro

| Test | duration |
|---|---|
|reuse_click_listener  |20 ns |
|set_new_click_listener  |38 ns |
|set_text  |486 ns |
|bind_on_constructor  |7 ns |


# Test code:


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
