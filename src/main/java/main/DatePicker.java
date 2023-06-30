package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

public class DatePicker extends JTextField {


    public static final String PLACEHOLDER = "yyyy/mm/dd...";
    public static final int YEAR_MONTH_FONT_SIZE = 18;
    public static final int NUMBER_OF_ROWS_IN_GRID = 7;
    public static final int PADDING = 5;
    private JPanel daysPanel;
    private final JLabel yearAndMonthText;
    private JDialog dateDialog;
    private YearMonth yearMonth;

    private final BufferedImage calendarIcon = IconReader.CALENDAR_ICON;

    public DatePicker (){
        setTextFieldProperties();
        yearAndMonthText = createYearMonthText();
        createDaysPanel();
        JPanel buttonsPanel = createButtonsPanel();
        JPanel rootPanel = createRootPanel(buttonsPanel);
        createCalendarDialog(rootPanel);
    }

    private void setTextFieldProperties() {
        setText(PLACEHOLDER);
        addActionOpenCalendarOnClick();
        setBackground(Color.WHITE);
    }

    private void createDaysPanel() {
        daysPanel = new JPanel(new GridLayout(NUMBER_OF_ROWS_IN_GRID, 0, PADDING, PADDING));
        daysPanel.setOpaque(false);

        addDaysToCalendar();
    }

    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        JButton next = addMonthsNavigationButton(">>", ()-> yearMonth.plusMonths(1));
        JButton previous = addMonthsNavigationButton("<<", ()-> yearMonth.minusMonths(1));
        buttonsPanel.add(previous);
        buttonsPanel.add (next);
        buttonsPanel.setOpaque(false);
        return buttonsPanel;
    }

    private void createNavigationButtonAction(JButton button, Supplier<YearMonth> yearMonthSupplier) {
        button.addActionListener(e->{
            yearMonth = yearMonthSupplier.get();
            yearAndMonthText.setText(getMonthYearValue());
            addDaysToCalendar();
        });
    }

    private JButton addMonthsNavigationButton(String label, Supplier<YearMonth> supplier) {
        JButton button = new JButton(label);
        button.setBackground(null);
        button.setBorder(null);
        button.setContentAreaFilled(false);
        button.setForeground(Color.BLUE);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        createNavigationButtonAction(button, supplier );
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setCursor(Cursor.getDefaultCursor());

            }
        });
        return button;
    }

    private JLabel createYearMonthText() {
        yearMonth = YearMonth.now();
        final JLabel yearAndMonthText = new JLabel(getMonthYearValue());
        yearAndMonthText.setFont(yearAndMonthText.getFont().deriveFont(Font.BOLD, YEAR_MONTH_FONT_SIZE));
        yearAndMonthText.setAlignmentX(0.5f);
        return yearAndMonthText;
    }

    private JPanel createRootPanel(JPanel buttonsPanel) {
        JPanel rootPanel = new JPanel();
        rootPanel.setBackground(Color.WHITE);
        rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.PAGE_AXIS));
        rootPanel.add(yearAndMonthText);
        rootPanel.add(Box.createRigidArea(new Dimension(PADDING, PADDING)));
        rootPanel.add(daysPanel);
        rootPanel.add(buttonsPanel);
        return rootPanel;
    }

    private void addActionOpenCalendarOnClick() {
        DatePicker datePicker = this;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dateDialog.pack();
                dateDialog.setLocationRelativeTo(datePicker);
                dateDialog.setVisible(true);
            }
        });
    }

    private void createCalendarDialog(JPanel datePanel) {
        dateDialog = new JDialog();
        dateDialog.setContentPane(datePanel);
        dateDialog.setUndecorated(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(calendarIcon, getWidth()-calendarIcon.getWidth()-4, (getHeight()-calendarIcon.getHeight())/2, null);
        setMargin(new Insets(2, 0, 2, calendarIcon.getWidth()+4));
    }

    private String getMonthYearValue() {
        return yearMonth.getMonth().toString().toLowerCase() + ", " + yearMonth.getYear();
    }

    private void addDaysToCalendar() {
        daysPanel.removeAll();
        int index =0;
        index = addDaysHeaders(index);
        index = addFillerDaysBeforeFirstDayOfMonth( index);
        index = addDayCell(index);
        addFillerDaysAfterLastDayOfMonth(index);

    }

    private int addDayCell( int index) {
        int numberOfDays = yearMonth.lengthOfMonth();
        for (int dayOfMonth = 1; dayOfMonth<= numberOfDays; dayOfMonth++){
            JLabel day = new JLabel("" + dayOfMonth);
            day.setFont(day.getFont().deriveFont(Font.PLAIN, 20f));
            day.setHorizontalAlignment(SwingConstants.CENTER);
            addDayCellMouseListener(day, dayOfMonth);
            daysPanel.add (day);
            index++;
        }
        return index;
    }

    private void addFillerDaysAfterLastDayOfMonth(int index) {
        while(index < 49){
            daysPanel.add(new JLabel());
            index++;
        }
    }

    private void addDayCellMouseListener(JLabel day, int dayOfMonth) {
        day.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                day.setBackground(Color.CYAN);
                day.setOpaque(true);
                day.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                day.setBackground(null);
                day.setOpaque(false);
                day.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                dateDialog.setVisible(false);
                setText(LocalDate.of(yearMonth.getYear(), yearMonth.getMonth(), dayOfMonth).format(DateTimeFormatter.ISO_LOCAL_DATE));
            }
        });
    }

    private int addFillerDaysBeforeFirstDayOfMonth(int index) {
        LocalDate firstDay = yearMonth.atDay(1);
        DayOfWeek dayOfWeek = firstDay.getDayOfWeek();
        int dayOfWeekIndex = dayOfWeek.getValue() -1;
        for (int i = 0; i< dayOfWeekIndex; i++){
            daysPanel.add(new JLabel());
            index++;
        }
        return index;
    }

    private int addDaysHeaders(int index) {
        for (DayOfWeek day : DayOfWeek.values()) {
            JLabel label = new JLabel(day.toString().toLowerCase().substring(0, 3));
            label.setHorizontalAlignment(SwingConstants.CENTER);

            daysPanel.add(label);
            index++;
        }
        return index;
    }

}
