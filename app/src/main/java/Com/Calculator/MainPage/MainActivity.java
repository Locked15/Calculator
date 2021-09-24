package Com.Calculator.MainPage;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import org.mariuszgromada.math.mxparser.*;


/**
 * Главный класс программы.
 */
public class MainActivity extends AppCompatActivity
{
    /**
     * Словарь, содержащий числа.
     */
    private final Map<View, String> numbers = new HashMap<>(1);

    /**
     * Словарь, содержащий операторы выражения.
     */
    private final Map<View, String> operators = new HashMap<>(1);

    /**
     * Элемент, содержащий введенное выражение.
     * <p>
     * Так как мы не можем обратиться к элементу до инициализации, ...
     * ... то инициализируем его в событии "onCreate".
     */
    private TextView answerBlock;

    /**
     * Событие, происходящее при запуске приложения.
     *
     * @param savedInstanceState Сохранённое состояние приложения.
     */
    @Override
    @SuppressWarnings("RedundantCast")
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answerBlock = (TextView) findViewById(R.id.AnswerView);

        initializeDictionaries();
    }

    /**
     * Метод, нужный для инициализации словарей.
     */
    @SuppressWarnings("RedundantCast")
    private void initializeDictionaries()
    {
        // region Инициализация словаря с числами.
        numbers.put((View) findViewById(R.id.NumberZero), "0");
        numbers.put((View) findViewById(R.id.NumberOne), "1");
        numbers.put((View) findViewById(R.id.NumberTwo), "2");
        numbers.put((View) findViewById(R.id.NumberThree), "3");
        numbers.put((View) findViewById(R.id.NumberFour), "4");
        numbers.put((View) findViewById(R.id.NumberFive), "5");
        numbers.put((View) findViewById(R.id.NumberSix), "6");
        numbers.put((View) findViewById(R.id.NumberSeven), "7");
        numbers.put((View) findViewById(R.id.NumberEight), "8");
        numbers.put((View) findViewById(R.id.NumberNine), "9");
        //endregion

        //region Инициализация словаря с операциями.
        operators.put((View) findViewById(R.id.ActionPlus), "+");
        operators.put((View) findViewById(R.id.ActionMinus), "-");
        operators.put((View) findViewById(R.id.ActionMultiply), "*");
        operators.put((View) findViewById(R.id.ActionDivide), "/");
        operators.put((View) findViewById(R.id.ActionLeftBrace), "(");
        operators.put((View) findViewById(R.id.ActionRightBrace), ")");
        operators.put((View) findViewById(R.id.ActionDegree), "^");
        //endregion
    }

    /**
     * Событие, происходящее при нажатии на кнопку с цифрой.
     * Добавляет эту цифру в расчет.
     *
     * @param number Элемент, вызвавший событие.
     */
    @SuppressLint("SetTextI18n")
    public void numberAreClicked(View number)
    {
        //В идеале, это надо сделать через "StringBuilder":
        answerBlock.setText(answerBlock.getText() + numbers.get(number));

        checkAnswerLengthDown();
    }

    /**
     * Событие, происходящее при нажатии на кнопку с операцией (и точкой).
     * Добавляет соответствующий элемент в расчет.
     *
     * @param operation Операция, которая будет добавлена.
     */
    @SuppressLint("SetTextI18n")
    public void operationIsClicked(View operation)
    {
        //Аналогично числам, лучше всего сделать через "StringBuilder":
        answerBlock.setText(answerBlock.getText() + operators.get(operation));

        checkAnswerLengthDown();
    }

    /**
     * Метод для проверки и уменьшения размера текста в введенном выражении.
     */
    private void checkAnswerLengthDown()
    {
        //Делаем проверку на размер, затем на возможность снизить размер шрифта в данном случае:
        if (answerBlock.getText().length() > 10 && answerBlock.getText().length() < 22 &&
        (answerBlock.getText().length() - 1) % 3 == 0)
        {
            answerBlock.setTextSize((int) (answerBlock.getTextSize() / 3.5) - 10);
        }
    }

    /**
     * Метод для проверки и увеличения размера текста в веденном выражении.
     * <p>
     * Действует при удалении каких-либо символов.
     */
    private void checkAnswerLengthUp()
    {
        //Если метод был вызван, а в блоке ответа малое выражение, устанавливаем ...
        //... стандартный размер шрифта (60sp):
        if (answerBlock.getText().length() <= 10)
        {
            answerBlock.setTextSize(TypedValue.COMPLEX_UNIT_SP, 60);
        }

        //В ином случае просто увеличиваем его на 10 ...
        // ... (если, конечно, мы не вышли за верхний предел):
        else if (answerBlock.getText().length() % 3 == 0 && answerBlock.getText().length() < 25)
        {
            //Чтобы получить корректный размер оригинального шрифта, делим его:
            answerBlock.setTextSize(TypedValue.COMPLEX_UNIT_SP,
            (int) (answerBlock.getTextSize() / 3.5) + 10);
        }
    }

    /**
     * Событие, происходящее при нажатии на кнопку "DeleteLastCharButton".
     * Удаляет последний символ из расчета.
     *
     * @param view Элемент вызвавший событие.
     */
    public void deleteLastChar(View view)
    {
        if (answerBlock.getText().toString().length() == 0)
        {
            Toast notify = Toast.makeText(this, "Выражение отсутствует.", Toast.LENGTH_SHORT);
            notify.show();

            return;
        }

        answerBlock.setText(answerBlock.getText().subSequence(0,
        answerBlock.getText().length() - 1));

        checkAnswerLengthUp();
    }

    /**
     * Событие, происходящее при нажатии на кнопку "ClearAllButton".
     * Очищает весь расчет.
     *
     * @param view Элемент вызвавший событие.
     */
    public void clearAll(View view)
    {
        answerBlock.setText("");

        Toast notify = Toast.makeText(this, "Выражение удалено.", Toast.LENGTH_SHORT);
        notify.show();

        checkAnswerLengthUp();
    }

    /**
     * Событие, происходящее при нажатии на кнопку "GetAnswer".
     * Производит расчет ответа для заданного выражения.
     *
     * @param view Элемент, вызвавший событие.
     */
    @SuppressLint("SetTextI18n")
    public void calculateAnswer(View view)
    {
        Expression evaluate = new Expression(answerBlock.getText().toString());

        if (!evaluate.checkSyntax())
        {
            Toast.makeText(this, "В выражении обнаружена ошибка.",
            Toast.LENGTH_LONG).show();
        }

        else
        {
            Double value = evaluate.calculate();

            if (value == Math.floor(value))
            {
                Integer answer = (int)Math.round(value);

                answerBlock.setText((answer.toString()));
            }

            else
            {
                answerBlock.setText(value.toString());
            }
        }
    }
}
