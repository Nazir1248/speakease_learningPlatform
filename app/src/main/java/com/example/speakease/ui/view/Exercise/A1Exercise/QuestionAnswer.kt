import com.example.speakease.R


data class Question(
    val id: Int,
    val imageResId: Int,
    val audioResId: Int,
    val options: List<String>,
    val correctAnswer: String
)


fun getQuestionById(exerciseNumber: Int): Question {
    return when (exerciseNumber) {
        1 -> Question(
            id = 1,
            imageResId = R.drawable.question1, // Replace with your image resource
            audioResId = R.raw.question1_audio, // Replace with your audio resource
            options = listOf("Option A", "Option B", "Option C"),
            correctAnswer = "Option A"
        )
        2 -> Question(
            id = 2,
            imageResId = R.drawable.question2,
            audioResId = R.raw.question2_audio,
            options = listOf("Option A", "Option B", "Option C"),
            correctAnswer = "Option B"
        )
        3 -> Question(
            id = 3,
            imageResId = R.drawable.question3,
            audioResId = R.raw.question3_audio,
            options = listOf("Option A", "Option B", "Option C"),
            correctAnswer = "Option C"
        )
        4 -> Question(
            id = 4,
            imageResId = R.drawable.question4,
            audioResId = R.raw.question4_audio,
            options = listOf("Option A", "Option B", "Option C"),
            correctAnswer = "Option A"
        )
        5 -> Question(
            id = 5,
            imageResId = R.drawable.question5,
            audioResId = R.raw.question5_audio,
            options = listOf("Option A", "Option B", "Option C"),
            correctAnswer = "Option B"
        )
        6 -> Question(
            id = 6,
            imageResId = R.drawable.question5,
            audioResId = R.raw.question5_audio,
            options = listOf("Option A", "Option B", "Option C"),
            correctAnswer = "Option C"
        )
        else -> throw IllegalArgumentException("Question not found")
    }
}