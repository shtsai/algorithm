/*
    Calendar -> add Events and notify. Event -> time, user-1 

    Rubrik
    09/27/2017
*/

class Calendar {
    private class Event {
        Time time;
        String des;
        User user;
        
        public Event (Time time, String des, User user) {
            this.time = time;
            this.des = des;
            this.user = user;
        }
        
        @Override
        public int compare(Event a, Event b) {  // acsending order
            return a.time - b.time;
        }
    }

    List<Event> list;
    Event comingEvent;
    
    public Calendar() {
        list = new PriorityQueue<Event>();
    }
    
    public void addEvent(Time time, String des, User user) {
        Event newEvent = new Event(time, des, user);
        list.offer(newEvent);
        comingEvent = list.peek();
    }
    
    public void Notify() {
        while (true) {
            Time cur = getCurrentTime();    // API to get current time
            if (cur == comingEvent.time) {
                notify(comingEvent.user);   // APT to notify user
                
                list.remove(comingEvent);
                comingEvent = list.peek();
            }         
        }
    }

}













