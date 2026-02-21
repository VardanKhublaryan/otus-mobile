package components;

public interface PopUp<T> {

   T shouldBeVisible();

   T shouldNotBeVisible();
}
