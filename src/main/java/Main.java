import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Main {
    public static void main(String[] args) {

        // обновление правил для часовых поясов (например, отмена зимнего времени в Москве)
        // в Андроиде производится вендором телефона, если он захочет

//        oldTime();
        newTime();
    }

    public static void oldTime(){
        System.out.println(TimeZone.getDefault().getDisplayName());
        System.out.println(TimeZone.getDefault().getID());
        System.out.println(TimeZone.getTimeZone("Europe/Moscow"));

        // Точнее, для измерения длительности
        System.out.println(System.nanoTime());
        // Unix time
        long now = System.currentTimeMillis();
        System.out.println(now);

        Date dateNow = new Date(now);
        System.out.println(dateNow);

        long past = 1234567910111L;
        Date datePast = new Date();
        datePast.setTime(past);
        System.out.println(datePast);

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
//        calendar.setTimeZone();
        calendar.setTimeInMillis(now);
        System.out.println(calendar);
        // Во время конвертации в строку берет дефолтное время (?), поэтому не правильно
        System.out.println(calendar.getTime() + " " + calendar.getTimeZone());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        System.out.println("UTC: " + simpleDateFormat.format(calendar.getTime()));
        System.out.println("UTC: " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND));

        // не сбрасывает миллисекунды при устанвке через даты
        Calendar calendar2 = Calendar.getInstance(TimeZone.getDefault());
        calendar2.setLenient(false); // отключает угадывание при ошибках при вводе
        calendar2.set(2016, Calendar.APRIL, 20, 12, 0, 0);
        System.out.println(calendar2.getTimeInMillis());
        calendar2.set(Calendar.MILLISECOND, 0);
        System.out.println(calendar2.getTimeInMillis());
    }

    // т.к. Date не immutable делают таким образом, чтобы не взломали
    private Date created;
    public void dateCloning(Date created){
        this.created = (Date) created.clone();
    }

    public static void newTime(){

        // Похож на Date. При toString выводит время для UTC
        Instant instant = Clock.systemDefaultZone().instant();
        System.out.println(instant);

        Clock clock3 = Clock.system(ZoneId.of("Europe/Paris"));
        System.out.println(clock3.getZone());

        Clock clock2 = Clock.systemDefaultZone();
        LocalDateTime localDateTime = LocalDateTime.now(clock2);
        System.out.println(localDateTime);

        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        System.out.println(zonedDateTime);
    }
}
