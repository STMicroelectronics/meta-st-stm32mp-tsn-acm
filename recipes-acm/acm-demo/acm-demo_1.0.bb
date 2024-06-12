SUMMARY = "ACM Demo"
LICENSE = "TTTECH-license"

SRC_URI = "${TSN_ACM_SRC_URI}"
SRCREV = "${TSN_ACM_SRCREV}"

TTTECH_DIR = "ngn.ngn-demo/acm-demo"
S = "${WORKDIR}/git/${TTTECH_DIR}"

PR = "st-1.6.7"

EXTRA_OEMAKE = " DESTDIR=${D} "
EXTRA_OEMAKE += "CFLAGS='${CFLAGS} -Wno-error=format-security'"

#TODO: temporary to bypass kernel module
CFLAGS:append = "-I${TSN_ACM_LAYER}/recipes-acm/acm-demo/files/includes"

do_install () {
	cd ${S}
	oe_runmake install
}
