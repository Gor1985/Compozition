package com.android.compozition.Presentation

import android.app.Application
import android.content.res.ColorStateList
import android.media.MediaFormat.KEY_LEVEL
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.compozition.Domain.entity.GameResults
import com.android.compozition.Domain.entity.GameSettings
import com.android.compozition.Domain.entity.Level
import com.android.compozition.Domain.entity.Question
import com.android.compozition.Presentation.GameFragment.Companion.newInstance
import com.android.compozition.R
import com.android.compozition.databinding.ActivityMainBinding.inflate
import com.android.compozition.databinding.FragmentGameBinding
import com.android.compozition.databinding.FragmentWelcomeBinding
import java.lang.RuntimeException



class GameFragment : Fragment() {
    private lateinit var level: Level

    private val args by navArgs<GameFragmentArgs>()


    private val viewModelFactory by lazy {
        val args=GameFragmentArgs.fromBundle(requireArguments())
        GameViewModelFactory(args.level, requireActivity().application)
    }

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[GameViewModel::class.java]
    }

    private val tvOptions by lazy {
        mutableListOf<TextView>().apply {
            add(binding.tvOption1)
            add(binding.tvOption2)
            add(binding.tvOption3)
            add(binding.tvOption4)
            add(binding.tvOption5)
            add(binding.tvOption6)
        }
    }
    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentGameBinding == null")

  //  override fun onCreate(savedInstanceState: Bundle?) {
     //   super.onCreate(savedInstanceState)
     //   parseArgs()
  //  }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
observeViewModel()

        setClickListenersToOptions()

    }
private fun setClickListenersToOptions(){
for (tvOption in tvOptions){
    tvOption.setOnClickListener {
        viewModel.chooseAnswer(tvOption.text.toString().toInt())
    }
}
}
    private fun observeViewModel() {
        viewModel.question.observe(viewLifecycleOwner) {
            binding.tvSum.text = it.sum.toString()
            binding.tvLeftNumber.text = it.visibleNumber.toString()
            for (i in 0 until tvOptions.size)
                tvOptions[i].text = it.options[i].toString()
        }
        viewModel.percentOfRightAnswers.observe(viewLifecycleOwner) {
            binding.progressBar.setProgress(it, true)
        }
        viewModel.enoughCount.observe(viewLifecycleOwner) {
            val colorResId = if (it) {
                android.R.color.holo_green_light
            } else {
                android.R.color.holo_red_dark
            }
            val color = ContextCompat.getColor(requireContext(), colorResId)
            binding.tvAnswersProgress.setTextColor(getColorByState(it))
        }
        //   binding.tvOption1.setOnClickListener {
        // launchGameFinishedFragment(
        //GameResults(
        //   true,
        ////    0,
        //  GameSettings(0, 0, 0, 0)
        //  )
        //   )
        //}
        viewModel.enoughPercent.observe(viewLifecycleOwner) {
            val color = getColorByState(it)
            binding.progressBar.progressBackgroundTintList = ColorStateList.valueOf(color)
        }
        viewModel.formattedTime.observe(viewLifecycleOwner) {
            binding.tvTimer.text = it
        }
        viewModel.minPercent.observe(viewLifecycleOwner) {
            binding.progressBar.secondaryProgress = it
        }
        viewModel.gameResult.observe(viewLifecycleOwner) {
            launchGameFinishedFragment(it)
        }
        viewModel.progressAnswers.observe(viewLifecycleOwner){
            binding.tvAnswersProgress.text=it
        }
    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun getColorByState(goodstate:Boolean):Int{
        val colorResId=if (goodstate){
            android.R.color.holo_green_light
        }else{
            android.R.color.holo_red_dark
        }
        return ContextCompat.getColor(requireContext(),colorResId)
    }
   // private fun parseArgs() {
      // requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
       //    level=it
      // }
  //  }

    private fun launchGameFinishedFragment(gameResult: GameResults) {
      //  requireActivity().supportFragmentManager.beginTransaction()
      //      .replace(R.id.main_container, GameFinishedFragment.newInstance(gameResult))
       //     .addToBackStack(null)
        //    .commit()

      //  val args=Bundle().apply {
      //      putParcelable(GAME_RESULT, gameResult)
     //   }
        findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameFinishedFragment(gameResult))
    }




    companion object {

        const val NAME = "GameFragment"
         const val KEY_LEVEL = "level"
     const val GAME_RESULT="gameresult"
        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, level)
                }
            }
        }
    }
}

