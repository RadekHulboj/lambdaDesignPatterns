1)
Najpierw dodaj do SAM -> S event(S currentState, E evNumber, Consumer<S> function);
dzieki funkcji bedziemy mogli dostac na event nowy stan maszyny ktory wykorzystamy, albo inczej
ustawimy currentState dla nastpnego eventu
2)
Wtedy ustawimy poprawne dzialanie maszyny stanow i nie dzialanie w przypadku gdy event nie moze sie na tym
stanie odbyc.
3)
Z ta technika stan bierzacy maszyny stanow jest tak naprawde przymany u callera czyli u klienta, tu trzeba bedzie sie zastanowic
jak to przerzucic do maszyny stanow. To bedzie trudne zadanie.
