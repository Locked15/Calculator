package Com.Calculator.MainPage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

/**
 * Главный класс программы.
 */
public class MainActivity extends AppCompatActivity
{
    /**
     * Событие, происходящее при запуске приложения.
     *
     * @param savedInstanceState Сохранённое состояние приложения.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    /**
     * Событие, происходящее при нажатии на кнопку с цифрой.
     * Добавляет эту цифру в расчет.
     *
     * @param number Элемент, вызвавший событие.
     */
    public void numberAreClicked(View number)
    {
    
    }
    
    /**
     * Событие, происходящее при нажатии на кнопку с операцией (и точкой).
     * Добавляет соответствующий элемент в расчет.
     *
     * @param operation Операция, которая будет добавлена.
     */
    public void operationIsClicked(View operation)
    {
    
    }
    
    /**
     * Событие, происходящее при нажатии на кнопку "DeleteLastCharButton".
     * Удаляет последний символ из расчета.
     *
     * @param view Элемент вызвавший событие.
     */
    public void deleteLastChar(View view)
    {
    
    }
    
    /**
     * Событие, происходящее при нажатии на кнопку "ClearAllButton".
     * Очищает весь расчет.
     *
     * @param view Элемент вызвавший событие.
     */
    public void clearAll(View view)
    {
    
    }
    
    /**
     * Событие, происходящее при нажатии на кнопку "GetAnswer".
     * Производит расчет ответа для заданного выражения.
     *
     * @param view Элемент, вызвавший событие.
     */
    public void calculateAnswer(View view)
    {
    
    }
}
