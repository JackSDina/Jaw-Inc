
section .text
    global _start

_start:
    mov rax, 0x39
    syscall
    jmp _start
