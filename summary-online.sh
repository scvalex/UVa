#!/bin/bash

function ranking {
    tools/query-uva.py ranking $1
}

function verdict {
    tools/query-uva.py problem $1 $2
}

dirs=$(find . -type d -a ! -name '.?*' -o -name '.?*' -a ! -prune)
printf "%10s | %4s | %4s | Title\n" "Problem" \
       "Alex ($(ranking scvalex))" \
       "Paul ($(ranking paulg))"
printf -- "-------------------------------------------------------\n"
for d in $(ls -v); do
    if [ -d ${d} -a ! -f ${d}/.ignore ]; then
        title=$(head -n1 ${d}/README.md)
        printf "%10s |" ${d}
        verdictA=$(verdict scvalex ${d})
        if [ "x${verdictA}" = "xAccepted" ]; then
            printf " %4s | " "AC"
            countAlex=$(expr ${countAlex} + 1)
        elif [ "x${verdictA}" = "xn/a" ]; then
            printf "      | "
        else
            printf "%4s | " "${verdictA}"
        fi

        verdictP=$(verdict paulg ${d})
        if [ "x${verdictP}" = "xAccepted" ]; then
            printf "%4s | " "AC"
            countPaul=$(expr ${countPaul} + 1)
        elif [ "x${verdictP}" = "xn/a" ]; then
            printf "     | "
        else
            printf "%4s | " "${verdictP}"
        fi
        printf "%s\n" "${title}"
    fi
done
printf -- "-------------------------------------------------------\n"
printf "%10s | %3d  | %3d  |\n" "Total" "${countAlex}" "${countPaul}"
