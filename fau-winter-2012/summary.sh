#!/bin/sh

dirs=$(find . -type d -a ! -name '.?*' -o -name '.?*' -a ! -prune)
printf "%10s | %4s | %4s | Title\n" "Problem" "Alex" "Paul"
printf -- "-------------------------------------------------------\n"
for d in $(ls -v); do
    if [ -d "${d}" -a ! -f "${d}/.ignore" ]; then
        if [ -f "${d}/.solved" ]; then
            author=$(head -n1 ${d}/.solved)
        else
            author="na"
        fi
        title=$(head -n1 ${d}/README.md)
        if [ "x${author}" = "xpaulg" ]; then
            printf "%10s | %4s | %4s | %s\n" ${d} "" "X " "${title}"
            countPaul=$(expr ${countPaul} + 1)
        elif [ "x${author}" = "xscvalex" ]; then
            printf "%10s | %4s | %4s | %s\n" ${d} "X " "" "${title}"
            countAlex=$(expr ${countAlex} + 1)
        else
            printf "%10s | %4s | %4s | %s\n" ${d} "" "" "${title}"
        fi
    fi
done
printf -- "-------------------------------------------------------\n"
printf "%10s | %3d  | %3d  |\n" "Total" "${countAlex}" "${countPaul}"

