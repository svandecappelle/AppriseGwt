AppriseGwt
==========

Apprise integration for gwt projects


# Getting started: 
```
// Create a popup
Popup popup = new Popup();

// Make any widget
FlexTable form = new FlexTable();
form.setWidget(0, 0, new Label("Namesdljgdflgbdsfdsjgdfblfdsngldbldsnvjldfbnfdvndln kdfjbldf,bjfbndf:vldfivnfkjvbdf"));
form.setWidget(1, 0, new Label("Firstname"));
form.setWidget(0, 1, new TextBox());
form.setWidget(1, 1, new TextBox());

// Put the widget into the popup
popup.setWidget(form);

// Show the popup
popup.show();

// It also works with any other widgets
Popup popup2 = new Popup();
popup2.setWidget(new Label("test"));
popup2.show();
```

*If more than one popup is showing, this is the last openned wich is over others and all others are behind is own overlay*
