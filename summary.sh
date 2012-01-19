#!/bin/sh

dirs=$(find . -type d -a ! -name '.?*' -o -name '.?*' -a ! -prune)
printf "%10s | %4s | %4s | Title\n" "Problem" "Alex" "Paul"
printf -- "-------------------------------------------------------\n"
for d in *; do
    if [ -d ${d} ]; then
        email=$(git log --reverse --pretty='%ae' ${d}/README.md | tail -n1)
        author=$(echo ${email} | cut -d '.' -f1 | cut -d '@' -f1)
        title=$(head -n1 ${d}/README.md)
        if [ "x${author}" = "xpaul" ]; then
            printf "%10s | %4s | %4s | %s\n" ${d} "" "X " "${title}"
            countPaul=$(expr ${countPaul} + 1)
        else
            printf "%10s | %4s | %4s | %s\n" ${d} "X " "" "${title}"
            countAlex=$(expr ${countAlex} + 1)
        fi
    fi
done
printf -- "-------------------------------------------------------\n"
printf "%10s | %3d  | %3d  |\n" "Total" "${countAlex}" "${countPaul}"
