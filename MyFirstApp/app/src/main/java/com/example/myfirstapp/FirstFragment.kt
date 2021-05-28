package com.example.myfirstapp

            import android.os.Bundle
                    import androidx.fragment.app.Fragment
                    import android.view.LayoutInflater
                    import android.view.View
                    import android.view.ViewGroup
                    import android.widget.Button
            import android.widget.TextView
            import android.widget.Toast
            import androidx.navigation.fragment.findNavController

            /**
             * A simple [Fragment] subclass as the default destination in the navigation.
             */
            class FirstFragment : Fragment() {

                override fun onCreateView(
                        inflater: LayoutInflater, container: ViewGroup?,
                        savedInstanceState: Bundle?
                ): View? {
                    // Inflate the layout for this fragment
                    return inflater.inflate(R.layout.fragment_first, container, false)
                }

                override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
                    super.onViewCreated(view, savedInstanceState)

                    view.findViewById<Button>(R.id.random_first).setOnClickListener {
            val myToast = Toast.makeText(context, "Hello Toast", Toast.LENGTH_SHORT)
                        myToast.show()
                        view.findViewById<Button>(R.id.count_button).setOnClickListener {
                            countMe(view)

                        }
        }
    }

                private fun countMe(view: View) {
                    var showCountTextView = view.findViewById<TextView>(R.id.textview_first)
                    val countString = showCountTextView.text.toString()
                    var count = countString.toInt()
                    val count1 = count++
                    count1
                    count.toString().also { showCountTextView = it }
                }
            }