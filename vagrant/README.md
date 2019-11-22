# Deploying PlantUML Jar using Vagrant

1. Install [Vagrant](https://www.vagrantup.com/downloads.html) and [VirtualBox](https://www.virtualbox.org/wiki/Downloads). On Mac you can `brew cask install vagrant`
1. Open a shell terminal and change directory to the `vagrant` directory
1. In folder `template` under `vagrant`, copy `settings.xml.template` file to `settings.xml`. In the copied file (`settings.xml`), replace `USERNAME` with your GitHub username, and `TOKEN` with your personal access token (must have `repo`, `write:packages`, `read:packages`, and `delete:packages` scopes) [more info](https://help.github.com/en/github/managing-packages-with-github-package-registry/configuring-apache-maven-for-use-with-github-package-registry).
1. Run `vagrant up`. It will take longer the first time only.
1. Now, run `vagrant ssh` to login to the VM
1. We can now start the building process. Run `rm -rf plantuml; git clone https://github.com/jgraph/plantuml.git; cd plantuml; ant mvn-deploy-jar`. Or, using maven `mvn deploy -DskipTests=true -Dmaven.javadoc.skip=true -B -V`.

Note: You can shutdown the VM using `vagrant halt`

# Using the same VM to deploy again

1. Start Vagrant VM `vagrant up`
1. Only last step is needed (`rm -rf plantuml; git clone https://github.com/jgraph/plantuml.git; cd plantuml; ant mvn-deploy-jar`).