#!/bin/bash

set -e

# default_prompt "Language" lang cpp
function default_prompt {
    echo -n "$1 [$3]: "
    read $2
    [ ! "x${!2}" = "x" ] || eval "$2=\"$3\""
}

default_prompt "Language" lang "cpp"
default_prompt "Number" num "000"
default_prompt "URL" url "http://www.google.com/"
default_prompt "Long Name" longname "My Problem"
default_prompt "Short Name" shortname "myproblem"

echo "Creating: ${num} - ${longname} as ${shortname} in ${lang}"

set -x

edir="empty-$lang"

# reproc infile
function reproc {
    m4 -DLONGNAME="${longname}" -DURL="${url}" -DSHORTNAME="${shortname}" $1
}

mkdir ${num}

reproc "${edir}/README.md" > "${num}/README.md"
reproc "${edir}/Makefile" > "${num}/Makefile"

