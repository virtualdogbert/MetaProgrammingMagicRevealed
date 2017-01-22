//http://groovy-lang.org/metaprogramming.html#categories

import groovy.time.TimeCategory

use(TimeCategory)  {
    println 1.minute.from.now       
    println 10.hours.ago

    def someDate = new Date()       
    println someDate - 3.months

}

// public class TimeCategory {

//     public static Date plus(final Date date, final BaseDuration duration) {
//         return duration.plus(date);
//     }

//     public static Date minus(final Date date, final BaseDuration duration) {
//         final Calendar cal = Calendar.getInstance();

//         cal.setTime(date);
//         cal.add(Calendar.YEAR, -duration.getYears());
//         cal.add(Calendar.MONTH, -duration.getMonths());
//         cal.add(Calendar.DAY_OF_YEAR, -duration.getDays());
//         cal.add(Calendar.HOUR_OF_DAY, -duration.getHours());
//         cal.add(Calendar.MINUTE, -duration.getMinutes());
//         cal.add(Calendar.SECOND, -duration.getSeconds());
//         cal.add(Calendar.MILLISECOND, -duration.getMillis());

//         return cal.getTime();
//     }

//     ...
// }
