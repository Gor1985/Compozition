package com.android.compozition.Presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.android.compozition.Domain.entity.GameResults
import com.android.compozition.R
import com.android.compozition.databinding.FragmentGameFinishedBinding
import java.lang.RuntimeException



class GameFinishedFragment : Fragment() {

    private val args by navArgs<GameFinishedFragmentArgs>()

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("FragmentGameFinishedBinding == null")

   // override fun onCreate(savedInstanceState: Bundle?) {
    //    super.onCreate(savedInstanceState)
      //  parseArgs()
   // }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupClickListeners()
        bindViews()



    }


    private fun setupClickListeners() {
        binding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    private fun bindViews() {
        with(binding) {
            emojiResult.setImageResource(getSmileResId())
            tvRequiredAnswers.text = String.format(
                getString(R.string.required_score),
                args.gameResult.gameSettings.minCountOfRightAnswers
            )
            tvScoreAnswers.text = String.format(
                getString(R.string.score_answers),
                args. gameResult.countOfRightsAnswers
            )
            tvRequiredPercentage.text = String.format(
                getString(R.string.required_percentage),
                args.gameResult.gameSettings.minProcentOfRightAnswers
            )
            tvScorePercentage.text = String.format(
                getString(R.string.score_percentage),
                getPercentOfRightAnswers()
            )
        }
    }

    private fun getSmileResId(): Int {

            return if (args.gameResult.winner) {
                R.drawable.ic_smile
            } else {
                R.drawable.ic_sad
            }
        }


    private fun getPercentOfRightAnswers() = with(args.gameResult) {
        if (countOfQuestion == 0) {
            0
        } else {
            ((countOfRightsAnswers / countOfQuestion.toDouble()) * 100).toInt()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

   // private fun parseArgs() {
     //   requireArguments().getParcelable<GameResults>(KEY_GAME_RESULT)?.let {
       //     gameResult = it
      //  }
   // }

    private fun retryGame() {
       // requireActivity().supportFragmentManager.popBackStack(
      //      GameFragment.NAME,
       //     FragmentManager.POP_BACK_STACK_INCLUSIVE
        //)

       findNavController().popBackStack()
    }

    companion object {

        const val KEY_GAME_RESULT = "game_result"

        fun newInstance(gameResult: GameResults): GameFinishedFragment {
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_GAME_RESULT, gameResult)
                }
            }
        }
    }
}