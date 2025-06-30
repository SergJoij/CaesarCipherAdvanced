package com.caesar;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicSpinnerUI;
import javax.swing.text.Caret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.security.Key;
import java.text.AttributedCharacterIterator;
//import java.lang.*;


public class Inter extends JFrame {
    JTextArea inp_text, encr_text, decr_text;
    int key = 1;
    JSpinner spinner;
    int ECU = 90; // Англ Загл Верхн_граница (English Capital Upper)
    int ECB = 65; // Англ Загл Нижн_граница (English Capital Bottom)
    int ELU = 122; // Англ Строчн Верхн_граница (English Lowercase Upper)
    int ELB = 97; //Англ Строчн Нижн_граница (English Lowercase Bottom)

    int RCU = 1071; // Рус Загл Верхн_граница (Russian Capital Upper)
    int RCB = 1040; // Рус Загл Нижн_граница (Russian Capital Bottom)
    int RLU = 1103; // Рус Строчн Верхн_граница (Russian Lowercase Upper)
    int RLB = 1072; // Рус Строчн Нижн_граница (Russian Lowercase Bottom)


    public Inter() {
        super("Шифр Цезаря");
        super.setBounds(200, 200, 1000, 700);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Monotype Corsiva. Mistral.
        Font font_for_main_labels = new Font("TimesRoman", Font.PLAIN, 18);
        Font font_for_main_fields = new Font("Monotype Corsiva", Font.PLAIN, 26);
        Font font_for_main_buttons = new Font("Verdana", Font.PLAIN, 20);

        Color text_color_for_main_labels = Color.CYAN;
        Color text_color_for_main_fields = Color.MAGENTA;
        Color background_color = Color.BLACK;
        super.setBackground(background_color);

        JLabel name0 = new JLabel("Сдвиг");
        name0.setFont(font_for_main_labels);
        name0.setForeground(text_color_for_main_labels);
        JLabel name1 = new JLabel("Обычный текст");
        name1.setFont(font_for_main_labels);
        name1.setForeground(text_color_for_main_labels);
        JLabel name2 = new JLabel("Зашифрованный текст");
        name2.setFont(font_for_main_labels);
        name2.setForeground(text_color_for_main_labels);

        Border field_border_1 = BorderFactory.createMatteBorder(2,2,2,2, text_color_for_main_labels);
        Border field_border_2 = BorderFactory.createEmptyBorder(5,5,5,5);
        CompoundBorder field_border = new CompoundBorder(field_border_1, field_border_2);

        Border button_border = BorderFactory.createMatteBorder(4,4,4,4, text_color_for_main_labels);

        inp_text = new JTextArea();
        inp_text.setForeground(text_color_for_main_fields);
        inp_text.setBackground(background_color);
        inp_text.setBorder(field_border);
        inp_text.setFont(font_for_main_fields);
        inp_text.setLineWrap(true);
        inp_text.setCaretColor(text_color_for_main_fields);
        inp_text.putClientProperty("caretWidth", 4);
        encr_text = new JTextArea();
        encr_text.setForeground(text_color_for_main_fields);
        encr_text.setBackground(background_color);
        encr_text.setFont(font_for_main_fields);
        encr_text.setLineWrap(true);
        encr_text.setBorder(field_border);
        encr_text.setCaretColor(text_color_for_main_fields);
        encr_text.putClientProperty("caretWidth", 4);

        JButton encr_button = new JButton("Зашифровать");
        encr_button.setFont(font_for_main_buttons);
        encr_button.setForeground(text_color_for_main_labels);
        encr_button.setBackground(background_color);
        encr_button.setBorder(button_border);
        JButton decr_button = new JButton("Расшифровать");
        decr_button.setFont(font_for_main_buttons);
        decr_button.setForeground(text_color_for_main_labels);
        decr_button.setBackground(background_color);
        decr_button.setBorder(button_border);

        spinner = new JSpinner(new SpinnerNumberModel(1, -32, 32, 1));
        spinner.setFont(font_for_main_labels);
        Component spinner_parametres = spinner.getEditor().getComponent(0);
        spinner_parametres.setForeground(text_color_for_main_fields);
        spinner_parametres.setBackground(background_color);
        spinner.setBorder(field_border_1);
        spinner.getComponent(1).setBackground(background_color);
        spinner.getComponent(0).setBackground(background_color);
        // UIManager.getDefaults().put("Spinner.ButtonDarkShadow", Color.RED);
        //UIManager.getDefaults().put("Spinner.controlDkShadow", Color.RED);
        // System.out.println(UIManager.getColor("controlDkShadow"));

        Container color_people = super.getContentPane();
        color_people.setLayout(new GridBagLayout());
        color_people.setBackground(background_color);
        GridBagConstraints constraints = new GridBagConstraints();

        JPanel c1 = new JPanel(new BorderLayout(10, 1));
        c1.setBackground(background_color);

        constraints.insets = new Insets(0, 5, 2, 5);
        constraints.weightx = 0.9;
        constraints.weighty = 0.05;
        constraints.gridy   = 0  ;  // нулевая ячейка таблицы по вертикали
        constraints.gridx   = 0  ;
        constraints.anchor = GridBagConstraints.WEST;

        c1.add(name0, BorderLayout.LINE_START);
        c1.add(spinner, BorderLayout.LINE_END);
        color_people.add(c1, constraints);

        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 1;
        constraints.gridy = 1;
        color_people.add(name2, constraints);
        constraints.gridx = 0;
        color_people.add(name1, constraints);

        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 0.8;
        constraints.gridy = 2;
        color_people.add(inp_text, constraints);
        constraints.gridx = 1;
        color_people.add(encr_text, constraints);

        constraints.insets.top = 5;
        constraints.insets.bottom = 5;
        constraints.weighty = 0.1;
        constraints.gridy = 3;
        constraints.gridx = 0;
        color_people.add(encr_button, constraints);
        constraints.gridx = 1;
        color_people.add(decr_button, constraints);

        encr_button.addActionListener(new Encryption());
        decr_button.addActionListener(new Decipher());
        spinner.addChangeListener(new SpinnerChange());
    }

    class Decipher implements ActionListener { // расшифровка
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = encr_text.getText();
            int l = name.length();
            String new_name = "";
            TableModel result_table = new TableModel(name);
            for (int j = 0; j < 32; j++) {   // 32 варианта расшифровки
                for (int i = 0; i < l; i++) {  // проходим по строке
                    char symb = name.charAt(i);
                    if (Character.isLetter(symb) && key != 0) {
                        int diapason = diapasonForm(symb);
                        symb = correctShift(symb, diapason, j + 1);
                    }
                    new_name = new_name + symb;
                }   // закончили идти по строке по строке
                result_table.writeInformation(j, new_name);
                new_name = "";
            }
            result_table.setVisible(true);
        }
    }

    class Encryption implements ActionListener{  // шифровка
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = inp_text.getText();
            int l = name.length();
            String new_name = "";
            for (int i = 0; i < l; i++) {
                char symb = name.charAt(i);
                if (Character.isLetter(symb) && key != 0){ // если знак является буквой и сдвиг есть
                    int diapason = diapasonForm(symb);
                    // Спасибо замечательным Ё (1025) и ё (1105): Е, е (1045, 1077), а Ж,ж (1046, 1078)
                    symb = correctShift(symb, diapason);
                }
                else if (Character.isSpaceChar(symb) && key != 0){
                    continue;
                }
                new_name = new_name + symb;
            }
            encr_text.setText(new_name);
        }
    }

    class SpinnerChange implements ChangeListener{  // при смене значения
        @Override
        public void stateChanged(ChangeEvent e) {
            key = (Integer) spinner.getValue();
            System.out.println(key);
            System.out.println('ё' + 1);
        }
    }

    private char correctShift(char symbol, int diapason) {
        char g = symbol;
        int min_border = 0, max_border = 0, yo = 0, yo_b_border = 0;
        switch (diapason){
            case 1:
                min_border = ECB;
                max_border = ECU;
                break;
            case 2:
                min_border = ELB;
                max_border = ELU;
                break;
            case 3:
                min_border = RCB;
                max_border = RCU;
                yo = 1025;
                yo_b_border = 1045;   // E
                break;
            case 4:
                min_border = RLB;
                max_border = RLU;
                yo = 1105;
                yo_b_border = 1077;  // e
                break;
        }
        // Сам рассчёт
        if (key > 0) {
            for (int i = 1; i <= key; i++) {
                if (g != yo) {  // если не ё
                    g = (char) (g + 1);
                    // постпроверка
                    if (g > max_border)  // если привысило верхнюю границу
                        g = (char) (min_border);
                    else if (g == yo_b_border + 1) // если попало на то место, где дб ё
                        g = (char) yo;
                } else
                    g = (char) (yo_b_border + 1);
            }
        }
        else {
            for (int i = -1; i >= key; i--) {
                if (g != yo) {  // если ё
                    g = (char) (g - 1);
                    // постпроверка
                    if (g < min_border)  // если привысило верхнюю границу
                        g = (char) (max_border);
                    else if (g == yo_b_border) // если попало на то место, где дб ё
                        g = (char) yo;
                } else
                    g = (char) (yo_b_border);
            }
        }
        return g;
    }

    private char correctShift(char symbol, int diapason, int the_key) {
        char g = symbol;
        int min_border = 0, max_border = 0, yo = 0, yo_b_border = 0;
        switch (diapason){
            case 1:
                min_border = ECB;
                max_border = ECU;
                break;
            case 2:
                min_border = ELB;
                max_border = ELU;
                break;
            case 3:
                min_border = RCB;
                max_border = RCU;
                yo = 1025;
                yo_b_border = 1045;   // E
                break;
            case 4:
                min_border = RLB;
                max_border = RLU;
                yo = 1105;
                yo_b_border = 1077;  // e
                break;
        }
        // Сам рассчёт
        if (the_key > 0) {
            for (int i = 1; i <= the_key; i++) {
                if (g != yo) {  // если не ё
                    g = (char) (g + 1);
                    // постпроверка
                    if (g > max_border)  // если привысило верхнюю границу
                        g = (char) (min_border);
                    else if (g == yo_b_border + 1) // если попало на то место, где дб ё
                        g = (char) yo;
                } else
                    g = (char) (yo_b_border + 1);
            }
        }
        else {
            for (int i = -1; i >= the_key; i--) {
                if (g != yo) {  // если ё
                    g = (char) (g - 1);
                    // постпроверка
                    if (g < min_border)  // если привысило верхнюю границу
                        g = (char) (max_border);
                    else if (g == yo_b_border) // если попало на то место, где дб ё
                        g = (char) yo;
                } else
                    g = (char) (yo_b_border);
            }
        }
        return g;
    }

    public int diapasonForm (char symbol){
        int d;
        if(symbol >= ECB && symbol <= ECU) // англ загл
            d = 1;
        else if (symbol  >= ELB && symbol <= ELU) // англ строчные
            d = 2;
        else if (symbol  >= RCB && symbol <= RCU || symbol == 'Ё') // рус загл
            d = 3;
        else if (symbol  >= RLB && symbol <= RLU || symbol == 'ё') // рус строчные
            d = 4;
        else
            d = 0;  // на всякий, если ввод произойдет не на англ. или рус. языке
        return d;
    }

    public void FromRussianToEnglish() {
        spinner.setValue(1);
        spinner.setModel(new SpinnerNumberModel(1, -25, 25, 1));

    }

    public void FromEnglishToRussian() {
        spinner.setValue(1);
        spinner.setModel(new SpinnerNumberModel(1, -32, 32, 1));

    }
}
/* всплывающее окно (JOptionPane.PLAIN_MESSAGE - тип сообщения)
 JOptionPane.showMessageDialog(null, "сообщение", "название окна", JOptionPane.PLAIN_MESSAGE);
 */