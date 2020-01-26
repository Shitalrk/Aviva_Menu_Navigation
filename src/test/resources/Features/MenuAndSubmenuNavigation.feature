Feature: Menu Navigation Feature

  Scenario Outline: Verify_Menus and Submenues
  
     Given the User navigates to given website
	 When the user clicks on "<Menue>" menu 
	 Then Verify all submenues appears for menu "<Menue>" test
	 
	 Examples:
	 |Menue|
	 |Ubezpieczenia|
	 |Inwestycje|
	 |Emerytura|	
	 |Kontakt i pomoc|
	 