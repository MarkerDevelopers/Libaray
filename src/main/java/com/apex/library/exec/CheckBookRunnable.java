package com.apex.library.exec;

import com.apex.library.BookManager;
import com.apex.library.Library;
import com.apex.library.datatypes.LentType;

public class CheckBookRunnable implements Runnable {
    @Override
    public void run() {
        BookManager bmgr = Library.getInstance().getBookManager();

        bmgr.getBooks().values().stream().filter(b -> b.getLentInfo().getType() == LentType.LENT).forEach(b -> {
                    if (b.getLentInfo().isExpired()) {
                        //withdraw player every 50 sec (minecraft time 1h)
                        long time = b.getLentInfo().getExpiredIn();
                        long l = System.currentTimeMillis() - time;
                        if (l % (50 * 60 * 1000) == 0) {
                            //TODO eco api integration
                        }
                    }
                }
        );
    }
}
