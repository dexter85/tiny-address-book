Informazioni sulle tabelle:

	* “people__info” contiene tutti le informazioni generali del contatto.
	* “people__phones, people__emails” contengono i vari numeri di telefono e indirizzi emails che un contatto può avere, sono relazionati a “people__info”. Quando viene eliminato un utente a cascata vengono eliminati tutti i numeri di telefono e le varie email a esso associate.
	* “calendar__info”: al suo interno vengono salvati i vari appuntamenti.
	* “calendar__people”: Al suo interno vengono relazionatati appuntamenti con persone. Anche in questa tabella le relazioni sono impostate a cascata e quando viene eliminato un contatto o un evento i dati ad esso associati vengono eliminati.
	*“system__tags”: conserva i vari tag per emails, phones e calendar. Ho avuto la necessità di questa tabella perché mi sembrava inutile avere delle ripetizioni all'interno delle tabelle in oggetto( es.: “casa”, “lavoro”, …). 

@todo views
