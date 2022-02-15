Boards:
- TODO
- In Progress
- Controll
- Completed


Når du oppretter et nytt issue i gitlab issue trackeren skal det legges til i TODO.

Som en utvikler skal du plukke den øverste issuen fra TODO-Listen som er tildelt deg.
Når du har valgt denne issuen skal den bli flyttet til In Progress.

Før du begynner å kode må du lage en feature branch:

`git checkout -b feature/IssueId`

Eksempel: git checkout -b feature/23

Du kan nå begynne å utvikle.

For å legge til endringene bruker du

`git add .`

for å commite endringen bruker du

`git commit -m "message"`

Formatet på message burde være slik

[Added, Removed, Updated, Changed, Fixed] (...) #[1..]

Eksempel: Added jump ability to mario #32


Når du mener issuen er løst, må du merge det inn i development branch.

`git push origin feature/IssueId`

`git checkout dev`

`git merge feature/IssueId`

Du kan nå flytte issuen til Controll


Til personen som tester implementasjonen:

For å se remote branches:

`git branch -r`

For å laste ned en branch fra remote (gitlab)

`git checkout -t origin/feature/IssueId`

Etter at du har verifisert at implementasjonen oppfyller kravet:

`git checkout master`

`git merge feature/IssueId`

Flytt issue fra Controll til Completed


