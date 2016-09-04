package ca.ubc.cs.cpsc210.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
 
@RunWith(Suite.class)
@Suite.SuiteClasses({
  PhotoTest.class,
  AlbumTest.class,
  TagManagerTest.class,
  PhotoManagerTest.class,
  PhotoLibraryTest.class
})
public class AllTests {
    // the class remains completely empty, 
    // being used only as a holder for the above annotations
}
