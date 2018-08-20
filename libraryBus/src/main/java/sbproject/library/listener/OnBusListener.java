package sbproject.library.listener;

/**
 * The interface On bus listener.
 */
public interface OnBusListener {
    /**
     * On event.
     *
     * @param tag the tag
     * @param o   the o
     * @return the boolean
     */
    boolean onEvent(String tag, Object o);

    /**
     * On error.
     *
     * @param tag the tag
     * @param e   the e
     */
    void onError(String tag, Exception e);
}
