package htb.com.childrenapp.Framework.image;

/**
 * 接口：可被自动回收的drawable
 *
 */
public interface RecycleDrawable {

    public void setIsDisplayed(boolean isDisplayed);

    public void setIsCached(boolean isCached);
}
