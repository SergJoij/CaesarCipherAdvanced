package com.caesar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;

public class TableModel extends JFrame {
    Object[][] array;

    public TableModel(String e_text) {
        super("Расшифровка");
        super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Font font_for_table = new Font("TimesRoman", Font.PLAIN, 18);
        System.out.println(e_text.length());

        array = new String[32][2];
        // заполним
        for (int j = 0; j < array.length; j++){
            array[j][0] = String.valueOf(j + 1);
            array[j][1] = e_text;
        }
        String[] columnNames = {"Ключ", "Текст"};

        JTable table1 = new JTable(array, columnNames);
        table1.setFont(font_for_table);
        table1.setIntercellSpacing(new Dimension(4, 2));  // отступы текста в ячейках

        table1.getColumnModel().getColumn( 0 ).setMaxWidth(40);
        if (e_text.length() > 26) {
            table1.getColumnModel().getColumn(1).setMinWidth(e_text.length() * 12); // 400
        }
        else {
            table1.getColumnModel().getColumn(1).setMinWidth(450);
        }

        table1.setRowHeight(30); // e_text.length() * 15
        table1.setVisible(true);
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JScrollPane scrollPane = new JScrollPane(table1);  // прокрутка для таблицы
        System.out.println("Ширина таблицы " + table1.getPreferredSize());
        System.out.println("Ширина полоски " + scrollPane.getHorizontalScrollBar().getPreferredSize());
        System.out.println("Ширина Viewport " + scrollPane.getViewport().getPreferredSize());
        System.out.println("Ширина Viewport р" + scrollPane.getViewport().getWidth());

        JLabel encr_label = new JLabel(e_text);
        encr_label.setFont(font_for_table);

        Container container = getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        // добавляем компоненты
        container.add(encr_label);
        container.add(scrollPane);
        encr_label.setAlignmentX(Component.CENTER_ALIGNMENT);

        int width_of_frame = table1.getPreferredSize().width + scrollPane.getHorizontalScrollBar().getPreferredSize().height + 20;
        super.setBounds(200, 200, width_of_frame, 700);
    }


    public void writeInformation(int row, String text){
        array[row][1] = text;
    }
}

