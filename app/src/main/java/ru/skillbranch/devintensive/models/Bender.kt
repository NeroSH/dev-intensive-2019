package ru.skillbranch.devintensive.models

class Bender(var status: Status = Status.NORMAL, var question: Question = Question.NAME) {
    var wrongState = 0

    fun askQuestion(): String = when (question) {
        Question.NAME -> Question.NAME.question
        Question.PROFESSION -> Question.PROFESSION.question
        Question.MATERIAL -> Question.MATERIAL.question
        Question.BDAY -> Question.BDAY.question
        Question.SERIAL -> Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question
    }

    fun askInvalidQuestion(): String = when (question) {
        Question.NAME -> "Имя должно начинаться с заглавной буквы\n" + Question.NAME.question
        Question.PROFESSION -> "Профессия должна начинаться со строчной буквы\n" + Question.PROFESSION.question
        Question.MATERIAL -> "Материал не должен содержать цифр\n" + Question.MATERIAL.question
        Question.BDAY -> "Год моего рождения должен содержать только цифры\n" + Question.BDAY.question
        Question.SERIAL -> "Серийный номер содержит только цифры, и их 7\n" +  Question.SERIAL.question
        Question.IDLE -> Question.IDLE.question

    }

    fun listenAnsewer(answer: String): Pair<String, Triple<Int, Int, Int>> = when {
            question == Question.NAME && (answer.isEmpty() || answer.first().isLowerCase() || answer.first().isDigit()) ->
                "Имя должно начинаться с заглавной буквы\n${question.question}" to status.color
            question == Question.PROFESSION && (answer.isEmpty() || answer.first().isUpperCase() || answer.first().isDigit()) ->
                "Профессия должна начинаться со строчной буквы\n${question.question}" to status.color
            question == Question.MATERIAL && (answer.isEmpty() || answer.contains("[0-9]+".toRegex())) ->
                "Материал не должен содержать цифр\n${question.question}" to status.color
            question == Question.BDAY && (answer.isEmpty() || answer.contains("[a-zA-Zа-яА-Я]+".toRegex())) ->
                "Год моего рождения должен содержать только цифры\n${question.question}" to status.color
            question == Question.SERIAL && (answer.isEmpty() || answer.contains("[a-zA-Zа-яА-Я]+".toRegex()) || answer.length != 7) ->
                "Серийный номер содержит только цифры, и их 7\n${question.question}" to status.color
            question == Question.IDLE ->
                question.question to status.color
            else -> if (question.answers.contains(answer.toLowerCase())) {
                    question = question.nextQuestion()

                    "Отлично - ты справился\n${question.question}" to status.color
                } else {
                    if (wrongState < 3) {
                        wrongState += 1
                        status = status.nextStatus()
                        "Это неправильный ответ!\n${question.question}" to status.color
                    } else {
                        wrongState = 0
                        status = Status.NORMAL
                        "Это неправильный ответ. Давай все по новой\n${question.question}" to status.color
                    }
                }
        }



    enum class Status(val color: Triple<Int, Int, Int>) {
        NORMAL(Triple(255, 255, 255)),
        WARNING(Triple(255, 120, 0)),
        DANGER(Triple(255, 60, 60)),
        CRITICAL(Triple(255, 255, 0));

        fun nextStatus(): Status {
            return if (this.ordinal < values().lastIndex) {
                values()[this.ordinal + 1]
            } else {
                values()[0]
            }
        }
    }

    enum class Question(val question: String, val answers: List<String>) {
        NAME("Как меня зовут?", listOf("бендер", "bender")) {
            override fun nextQuestion(): Question = PROFESSION
        },
        PROFESSION("Назови мою профессию?", listOf("сгибальщик", "сгибатель", "bender")){
            override fun nextQuestion(): Question = MATERIAL
        },
        MATERIAL("Из чего я сделан?", listOf("метал", "дерево", "metal", "iron", "wood")){
            override fun nextQuestion(): Question = BDAY
        },
        BDAY("Когда меня создали?", listOf("3000", "2000")){
            override fun nextQuestion(): Question = SERIAL
        },
        SERIAL("Мой серийный номер?", listOf("2716057", "5248691")){
            override fun nextQuestion(): Question = IDLE
        },
        IDLE("На этом все, вопросов больше нет", listOf()){
            override fun nextQuestion(): Question = IDLE
        };

        abstract fun nextQuestion(): Question
    }
}
