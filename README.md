# How to integrate TSN-ACM layers from your OpenSTLinux distribution:
* PC $> cd layers/meta-st/
* PC $> git clone https://github.com/STMicroelectronics/meta-st-stm32mp-tsn-swch.git
* PC $> git clone https://github.com/STMicroelectronics/meta-st-stm32mp-tsn-acm.git
* PC $> cd ../..

# compilation of weston image
* PC $> MACHINE=stm32mp2 DISTRO=openstlinux-weston source layers/meta-st/scripts/envsetup.sh
* PC $> bitbake-layers add-layer ../layers/meta-st/meta-st-stm32mp-tsn-swch
* PC $> bitbake-layers add-layer ../layers/meta-st/meta-st-stm32mp-tsn-acm
* PC $> bitbake st-image-weston
