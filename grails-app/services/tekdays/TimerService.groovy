package tekdays

import grails.transaction.Transactional

@Transactional
class TimerService {

    private static boolean singleTask = true
    private static Timer t = new Timer()

    void TimerByRH(int s, int m, int h, int d) {

//        Timer t = new Timer('SINGLE TASK' + "(${period})")
        if (singleTask) {
            singleTask = false
            long period = 0;


            if (d > 0 && d <= 365) {
                period += (((d * 24) * 60) * 60)
                println period
            }
            if (h > 0 && h <= 24) {
                period += ((h * 60) * 60)
                println period
            }
            if (m > 0 && m <= 60) {
                period += (m * 60)
                println period
            }
            if (s > 0 && s <= 60) {
                period += s
                println period
            }
            period = period * 1000

            TimerTask task = new TimerTask() {
                @Override
                void run() {
                    Thread.currentThread().setName('SINGLE TASK' + "(${period})").toString()
                    println Thread.currentThread().toString()
                }
            }

            t.schedule(task, 0, period)

        } else {


            println "Task Updated"
            singleTask = true
            t.cancel()
            t = new Timer()
            TimerByRH(s, m, h, d)
        }

    }

    void TimerBySpring() {
        println 1
    }

}
