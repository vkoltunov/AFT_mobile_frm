package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by User on 4/5/2017.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        Preparation.class,
        test_Navigation.class,
        test_SaveAndShare.class,
        test_SimpleWorkflow.class,
        test_VersionCheck.class,
        test_Persistent.class,
        test_GoPro.class,
        test_Art.class,
        test_Animation.class,
        test_RateUs.class,
        test_Collage.class,
        test_Favorites.class,
        test_Watemark.class,
        test_Notifications.class,
        test_Compositions.class,
        test_Inspiration.class
})
public class TestsConfiguration {
}
