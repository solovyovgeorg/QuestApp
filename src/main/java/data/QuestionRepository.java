package data;

import org.example.exceptions.QuestAppException;
import org.example.model.Question;

import java.util.List;
/** Интерфейс репозитория для запроса данных по параметрам*/
public interface QuestionRepository {
    /** Возвращает модель Question по ее id, выбрасывает исключение при отсутствии таковой */
    Question getQuestionById(int id) throws QuestAppException;
    /** Возвращает список вариантов Question по конкретному Question */
    List<Question> getVariantsByQuestion(Question question);
}
