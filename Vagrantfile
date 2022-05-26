# -*- mode: ruby -*-
# vi: set ft=ruby :

# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.
Vagrant.configure("2") do |config|
  
  # Install vagrant plugin
  required_plugins = %w( vagrant-vbguest vagrant-disksize vagrant-docker-compose )
  _retry = false
  required_plugins.each do |plugin|
    unless Vagrant.has_plugin? plugin
      system "vagrant plugin install #{plugin}"
      _retry=true
    end
  end

  if (_retry)
    exec "vagrant " + ARGV.join(' ')
  end


  # The most common configuration options are documented and commented below.
  # For a complete reference, please see the online documentation at
  # https://docs.vagrantup.com.

  # Every Vagrant development environment requires a box. You can search for
  # boxes at https://vagrantcloud.com/search.
  config.vm.box = "generic/ubuntu2004"
  config.disksize.size = "20GB"


  # Disable automatic box update checking. If you disable this, then
  # boxes will only be checked for updates when the user runs
  # `vagrant box outdated`. This is not recommended.
  # config.vm.box_check_update = false

  # Create a forwarded port mapping which allows access to a specific port
  # within the machine from a port on the host machine. In the example below,
  # accessing "localhost:8080" will access port 80 on the guest machine.
  # NOTE: This will enable public access to the opened port
#   config.vm.network "forwarded_port", guest: 8080, host: 8080, disabled: false # spring boot
  config.vm.network "forwarded_port", guest: 3306, host: 3306, disabled: false # mysql
  config.vm.network "forwarded_port", guest: 5672, host: 5672, disabled: false # rabbitmq
  config.vm.network "forwarded_port", guest: 15672, host: 15672, disabled: false # rabbitmq admin panel

  config.ssh.forward_agent = true
  config.ssh.forward_x11 = true

  # Provider-specific configuration so you can fine-tune various
  # backing providers for Vagrant. These expose provider-specific options.
  # Example for VirtualBox:
  config.vm.provider "virtualbox" do |vb|
    vb.name = "java-vagrant-machine"
    
    vb.customize ["modifyvm", :id, "--natdnshostresolver1", "on"]
    vb.customize ["modifyvm", :id, "--natdnsproxy1", "on"]
    vb.customize ['modifyvm', :id, '--cableconnected1', 'on']

    # see: https://blog.rudylee.com/2014/10/27/symbolic-links-with-vagrant-windows/
    vb.customize ["setextradata", :id, "VBoxInternal2/SharedFoldersEnableSymlinksCreate//vagrant","1"]
    #vb.customize ["setextradata", :id, "VBoxInternal2/SharedFoldersEnableSymlinksCreate/.", "1"]

    vb.customize ["modifyvm", :id, "--accelerate3d", "on"]
    vb.customize ['modifyvm', :id, '--clipboard', 'bidirectional']
    vb.customize ['modifyvm', :id, '--draganddrop', 'bidirectional']

    vb.gui = false
    vb.memory = "4096"
    vb.cpus = 2
  end

  # Create a private network, which allows host-only access to the machine
  # using a specific IP.
  config.vm.network "private_network", ip: "192.168.33.10"

  # Create a public network, which generally matched to bridged network.
  # Bridged networks make the machine appear as another physical device on
  # your network.
  # config.vm.network "public_network"

  # Share an additional folder to the guest VM. The first argument is
  # the path on the host to the actual folder. The second argument is
  # the path on the guest to mount the folder. And the optional third
  # argument is a set of non-required options.

  config.vm.synced_folder "./", "/vagrant", fsnotify: true
  config.vm.synced_folder "./", "/git-root", type: "virtualbox"

  # If errors occur, try running "vagrant provision" manually
  # after "vagrant up"
  config.vm.provision :shell, inline: "sudo apt-get update"
  config.vm.provision :docker
  config.vm.provision :docker_compose

  config.vm.provision "shell", path: "./install-script.sh"

  # View the documentation for the provider you are using for more
  # information on available options.

  # Enable provisioning with a shell script. Additional provisioners such as
  # Ansible, Chef, Docker, Puppet and Salt are also available. Please see the
  # documentation for more information about their specific syntax and use.
  # config.vm.provision "shell", inline: <<-SHELL
  #   apt-get update
  #   apt-get install -y apache2
  # SHELL
  config.vm.provision "run", type: "shell", privileged: false, run: "always",
	inline: <<-SHELL
	  echo "=============================================="
	  echo "dependencies are ready !"
	  docker ps --format "{{.ID}}\t{{.Names}}\t{{.Ports}}"
	  echo "=============================================="
  SHELL
end
