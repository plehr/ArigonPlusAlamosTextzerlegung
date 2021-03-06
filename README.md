# Textzerlegung von Arigon Plus zu Alamos

Wenn man Einsätze aus der Workstation von Arigon Plus der Firma VOMATEC Innovations GmbH in die Software FE2 der Firma Alamos GmbH übernehmen möchte, kann man die Ausgabedatei nutzen.

## Vorbereitung Arigon Plus

Bitte prüfen Sie das [Betriebshandbuch](https://arigonplus.atlassian.net/wiki/spaces/APDOK/pages/1007548303/Einsatzdisposition) um die Ausgabedatei für Einsatzdaten zu aktivieren. Ich persönlich empfehle einen Windows-Freigabe im Netzwerk in Form des [FQDN](https://www.ionos.de/digitalguide/domains/domainverwaltung/fqdn-fully-qualified-domain-name/). Aus Sicherheitsgründen sollte die Freigabe auf Windows-Ebene geschützt sein und nur die Disponenten-Accounts sollten Dateien ablegen dürfen.



## Vorbereitung FE2

In FE2 muss ein neuer Alarmeingang eingerichtet werden. In diesem Szenario wird die "Datei-Überwachung (Echtzeit)" genutzt. Wie Sie diese Freigabe aktivieren, schlagen Sie bitte ebenfalls im [Betriebshandbuch](https://alamos-support.atlassian.net/wiki/spaces/documentation/pages/219480852/Datei-+berwachung+Echtzeit) nach. Als Einstellung empfehle ich hier die Optionen:

* Alte Dateien nach /old verschieben (zu aktivieren)
* Dateien älter als 7 Tage aus /old Ordner löschen (zu aktivieren)

Nach der Aktivierung des Alarmeingangs wird der Ordner /old erzeugt. Aus Sicherheitsgründen empfehle ich den Zugriff auf Filesystem-Ebene nur für den lokalen Systemdienst freizuschalten und die Disponenten hier auszusperren. Sonst könnte eine Historie der übermittelten Einsätze unberechtigterweise abgegriffen werden. 

Anschließend befolgen Sie bitte die Anleitung zur [eigenen Zerlegung](https://alamos-support.atlassian.net/wiki/spaces/documentation/pages/219480670/Eigene+Zerlegung+entwickeln). Anschließend können Sie Ihre Einheiten in FE2 entsprechend konfigurieren.

Wenn eine automatische Einsatzeröffnung in Arigon Plus genutzt wird, ist es ratsam den Benutzeraccount der automatischen Einsatzdisposition in FE2 auf eine [Blacklist](https://alamos-support.atlassian.net/wiki/spaces/documentation/pages/219480767/Blacklist) zu setzen.

## Test

Zum Test disponieren Sie einen Einsatz über Arigon Plus und prüfen den Alarmeingang in der FE2-Oberfläche.

## Nutzungshinweis

Die Nutzung von Arigon Plus und FE2 benötigen entsprechende Lizenzen der Firmen. Dieses Skript dürfen Sie kostenfrei und auf eigene Verantwortung verwenden. Ich empfehle die Nutzung in produktiven Umgebungen nicht und rate dringend von der Nutzung ab, wenn es um Menschenleben geht.

Wenn Sie sich dem Risiko und der Verantwortung bewusst sind, dürfen Sie meine Software gerne nutzen. Über eine Nachricht bei erfolgreicher  Nutzung würde ich mich freuen. Ebenfalls dürfen Sie diese Software weiterentwickeln oder verbessern, solange Sie auch Ihren Bestandteil als open Source auf GitHub zur freien Verwendung und unter gleicher Lizenz veröffentlichen. Ein Verweis auf dieses Repository ist obligatorisch.

## Testdaten

Alle Testdaten sind selbstverständlich frei erfunden und entsprechen keinen realen Einsätzen. Die Verwendung zur Entwicklung einer eigenen Schnittstelle ist unabhängig von dieser Software ohne Namensnennung ist nicht erlaubt.

## Selbst kompilieren?

Wenn Sie selbst entwickeln und kompilieren wollen, benötigen Sie die Testdaten, den Unit-Test und Abhängigkeiten:

* JUnit 4.12
* Hamcrest Core 1.3
* FE2 SDK Classes

Die Testdaten und den passenden Unit-Test [erhalten Sie von mir auf Anfrage](https://plehr.de/) und alle anderen Abhängigkeiten von der [Firma Alamos](https://alamos-support.atlassian.net/wiki/spaces/documentation/pages/219480670/Eigene+Zerlegung+entwickeln).
