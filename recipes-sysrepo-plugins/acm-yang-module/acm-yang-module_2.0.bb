DESCRIPTION = "Netopeer2 sysrepo plugin for ACM"
SECTION = "ACM"
LICENSE = "TTTECH-license"

SRC_URI = "${TSN_ACM_SRC_URI}"
SRCREV = "${TSN_ACM_SRCREV}"

TTTECH_DIR = "ngn.acm-yang"
S = "${WORKDIR}/git/${TTTECH_DIR}"

SRC_URI += "file://0001-ACM-YANG-Patch-to-support-arm64-bits-compilation.patch"

PV = "st-1.6.7"

DEPENDS = "libbase libyang libnetconf2 sysrepo coreutils openssh openssl openssh-native libbsd libacmconfig"

FILES:${PN} += "${libdir}/sysrepo/plugins/* /etc/netopeer2/*"

inherit cmake pkgconfig

# Specify any options you want to pass to cmake using EXTRA_OECMAKE:
OECMAKE_C_FLAGS = "${HOST_CC_ARCH} ${TOOLCHAIN_OPTIONS} ${TARGET_CPPFLAGS} -Wno-error=comment -Wno-error=cpp"
EXTRA_OECMAKE = " -DCMAKE_INSTALL_PREFIX=/usr -DCMAKE_BUILD_TYPE:String=Release -DSYSREPOCTL_EXECUTABLE=/usr/bin/sysrepoctl -DSYSREPOCFG_EXECUTABLE=/usr/bin/sysrepocfg -DCHMOD_EXECUTABLE=/bin/chmod"

do_install:append () {
    install -d ${D}/etc/netopeer2/yang
    install -m 0644 ${S}/model/acm.yang ${D}/etc/netopeer2/yang

    if [ "${libdir}" != "/usr/lib" ];
    then
        if [ -d ${D}/usr/lib ]; then
            mv ${D}/usr/lib ${D}/usr/lib64
        fi
    fi
}
INSANE_SKIP:${PN} = "file-rdeps"
