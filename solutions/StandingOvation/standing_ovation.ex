defmodule StandingOvation do
  @moduledoc false
  def by_line(path) do
    File.stream!(path)
    |> Stream.map(&String.trim/1)
    |> Stream.with_index
    |> Stream.map(fn ({line, index}) -> StandingOvation.calStandingPeopleNeeded "#{line}", "#{index}" end)
    |> Stream.run
  end

  def calStandingPeopleNeeded(stringPeople, index) do
    index = String.to_integer(index)
    if index > 0 do
      stringPeople = String.split(stringPeople, " ")
      stringPeople = String.split(Enum.at(stringPeople, 1), "", trim: true)
      stringPeople = stringPeople |> Enum.map(&String.to_integer/1)
      intPeople = stringPeople |> Enum.with_index()

      needed = 0

      needed = Enum.reduce(intPeople, 0, fn ({x, i}, need) ->
        sum = Enum.sum(Enum.slice(stringPeople, 0, i))
        if ((need + sum) < i) do
          need = i - sum
        end
        need
      end)

      IO.inspect(needed)

    end
  end

end

StandingOvation.by_line("../A-small-practice.in")