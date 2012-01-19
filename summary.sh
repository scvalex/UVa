#!/bin/sh

dirs=$(find . -type d -a ! -name '.?*' -o -name '.?*' -a ! -prune)
printf "%10s | %4s | %4s\n" "Problem" "Alex" "Paul"
for d in *; do
    if [ -d ${d} ]; then
        email=$(git log --reverse --pretty='%ae' ${d}/README.md | tail -n1)
        author=$(echo ${email} | cut -d '.' -f1 | cut -d '@' -f1)
        if [ "x${author}" = "xpaul" ]; then
            printf "%10s | %4s | %4s\n" ${d} "" "X "
            countPaul=$(expr ${countPaul} + 1)
        else
            printf "%10s | %4s | %4s\n" ${d} "X " ""
            countAlex=$(expr ${countAlex} + 1)
        fi
    fi
done
printf -- "-------------------------\n"
printf "%10s | %4d | %4d\n" "Total" "${countAlex}" "${countPaul}"
